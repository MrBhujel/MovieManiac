package com.example.moviemaniac.core.util

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import java.io.ByteArrayInputStream

object AdBlocker {
    private val adDomains = hashSetOf(
        "doubleclick.net", "google-analytics.com", "googlesyndication.com",
        "googleadservices.com", "googletagmanager.com", "quantserve.com",
        "zedo.com", "adservice.google.com", "onclickads.net", "proads.co",
        "popads.net", "popcash.net", "adsterra.com", "exoclick.com",
        "juicyads.com", "propellerads.com", "ad-maven.com", "bet365.com",
        "1xbet.com", "mostbet.com", "linebet.com", "melbet.com",
        "parimatch.com", "betway.com", "betfair.com", "bwin.com",
        "pokerstars.com", "888poker.com", "chatmate.tv", "chatmate.com",
        "whos.amung.us", "histats.com", "yandex.ru", "mail.ru",
        "steepto.com", "mndtrk.com", "clktraffic.com", "bittube.video",
        "coinhive.com", "crypto-loot.com", "adskeeper.co.uk", "mgid.com",
        "taboola.com", "outbrain.com", "revcontent.com", "adblade.com"
    )

    private val adKeywords = listOf(
        "/ads/", "popunder", "click", "tracker", "banner", "advert", 
        "analytics", "telemetry", "overlay", "popup", "moatads",
        "partner", "pixel", "sponsor", "videowrapper", "adframe"
    )

    fun shouldBlock(request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()?.lowercase() ?: return false
        
        // Block by domain
        if (adDomains.any { url.contains(it) }) return true
        
        // Block by keyword
        if (adKeywords.any { url.contains(it) }) return true
        
        return false
    }

    fun createEmptyResponse(): WebResourceResponse {
        return WebResourceResponse(
            "text/plain",
            "utf-8",
            ByteArrayInputStream("".toByteArray())
        )
    }

    fun getAdBlockingScript(): String {
        return """
            (function() {
                const blockSelectors = [
                    'div[class*="overlay"]', 'div[id*="overlay"]', 
                    'div[class*="popup"]', 'div[id*="popup"]',
                    'div[class*="ads"]', 'div[id*="ads"]',
                    'iframe[src*="chatmate"]', 'iframe[src*="ad"]',
                    'a[href*="click"]', 'div[style*="z-index: 2147483647"]',
                    '.video-ad-overlay', '.ad-skip-button'
                ];

                function removeAds() {
                    blockSelectors.forEach(selector => {
                        document.querySelectorAll(selector).forEach(el => {
                            el.remove();
                        });
                    });
                    
                    // Kill anti-adblock and popups
                    window.open = function() { return null; };
                    window.alert = function() { return null; };
                    
                    // Stop aggressive scripts from creating new elements
                    const observer = new MutationObserver((mutations) => {
                        mutations.forEach((mutation) => {
                            mutation.addedNodes.forEach((node) => {
                                if (node.nodeType === 1) {
                                    blockSelectors.forEach(selector => {
                                        if (node.matches(selector)) node.remove();
                                        node.querySelectorAll(selector).forEach(el => el.remove());
                                    });
                                }
                            });
                        });
                    });
                    observer.observe(document.body, { childList: true, subtree: true });
                }

                if (document.readyState === 'loading') {
                    document.addEventListener('DOMContentLoaded', removeAds);
                } else {
                    removeAds();
                }
            })();
        """.trimIndent()
    }
}