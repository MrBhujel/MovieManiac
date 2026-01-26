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
        "taboola.com", "outbrain.com", "revcontent.com", "adblade.com",
        "fastclick.net", "casalemedia.com", "yieldmo.com", "rubiconproject.com"
    )

    private val adKeywords = listOf(
        "/ads/", "popunder", "click", "tracker", "banner", "advert", 
        "analytics", "telemetry", "overlay", "popup", "moatads",
        "partner", "pixel", "sponsor", "videowrapper", "adframe",
        "prebid", "amazon-adsystem", "adnxs", "smartadserver",
        "jads.co", "ad-delivery", "adform", "adroll", "adverline"
    )

    fun shouldBlock(request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString()?.lowercase() ?: return false
        if (adDomains.any { url.contains(it) }) return true
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
                // 1. MOCK ENVIRONMENT
                window.canRunAds = true;
                window.isAdBlockActive = false;
                window.adsbygoogle = { push: function() {} };
                
                // 2. CSS INJECTION - Hide elements and prepare patch styling
                const style = document.createElement('style');
                style.innerHTML = `
                    .vidsrc-logo, #vidsrc-logo, [class*="logo"], [id*="logo"], 
                    .vjs-vidsrc-link, .vidsrc-link, .branding, [class*="branding"] {
                        display: none !important;
                        opacity: 0 !important;
                        visibility: hidden !important;
                    }
                    .logo-patch {
                        position: absolute !important;
                        background-color: black !important;
                        z-index: 2147483647 !important;
                        pointer-events: none !important;
                    }
                `;
                document.head.appendChild(style);

                function cleanup() {
                    // 3. APPLY BLACK PATCH OVER LOGO TEXT
                    // We search for elements containing 'VIDSRC' and cover them with a black box
                    const walker = document.createTreeWalker(document.body, NodeFilter.SHOW_TEXT, null, false);
                    let node;
                    while(node = walker.nextNode()) {
                        const content = node.textContent.toUpperCase();
                        if (content.includes('VIDSRC')) {
                            const parent = node.parentElement;
                            if (parent && parent.tagName !== 'SCRIPT' && parent.tagName !== 'STYLE') {
                                const rect = parent.getBoundingClientRect();
                                if (rect.width > 0 && rect.height > 0) {
                                    let patch = document.getElementById('patch-' + parent.className.split(' ')[0]);
                                    if (!patch) {
                                        patch = document.createElement('div');
                                        patch.className = 'logo-patch';
                                        patch.id = 'patch-' + parent.className.split(' ')[0];
                                        document.body.appendChild(patch);
                                    }
                                    patch.style.top = (rect.top + window.scrollY) + 'px';
                                    patch.style.left = (rect.left + window.scrollX) + 'px';
                                    patch.style.width = rect.width + 'px';
                                    patch.style.height = rect.height + 'px';
                                    
                                    // Also try to hide the parent directly
                                    parent.style.setProperty('color', 'black', 'important');
                                    parent.style.setProperty('background-color', 'black', 'important');
                                }
                            }
                        }
                    }

                    window.open = function() { return null; };
                }

                cleanup();
                setInterval(cleanup, 500);
            })();
        """.trimIndent()
    }
}
