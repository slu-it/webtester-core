package info.novatec.testit.webtester.browser.proxy;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.BrowserFactory;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.browser.WebDriverBrowser;


/**
 * Factory class for creating Firefox {@link Browser} objects using the Marionette driver.
 *
 * @see Browser
 * @see MarionetteDriver
 * @see GeckoDriverService
 * @since 1.2
 */
public class MarionetteFactory implements BrowserFactory {

    private ProxyConfiguration proxyConfiguration;

    /**
     * Creates a new {@link Browser} object for a Firefox Marionette web browser with a
     * default {@link GeckoDriverService}. Any desired capabilities will be
     * initialized as well.
     * <p>
     * Currently the following capabilities are configurable:
     * <ul>
     * <li>proxy setting using a {@link ProxyConfiguration}</li>
     * </ul>
     * The default profile includes the deactivation of native events for more
     * performance as well as the acceptance of untrusted certificates.
     *
     * @return the created {@link Browser}.
     * @see Browser
     * @see BrowserFactory
     * @since 1.2
     */
    @Override
    public Browser createBrowser() {

        GeckoDriverService service = GeckoDriverService.createDefaultService();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        setOptionalProxyConfiguration(capabilities);

        return createBrowser(new MarionetteDriver(service, capabilities));
    }

    /**
     * Creates a new {@link Browser} object for a Firefox Marionette web browser
     * using the given port for the driver server. Any desired capabilities will
     * be initialized as well.
     * <p>
     * Currently the following capabilities are configurable:
     * <ul>
     * <li>proxy setting using a {@link ProxyConfiguration}</li>
     * </ul>
     * The default profile includes the deactivation of native events for more
     * performance as well as the acceptance of untrusted certificates.
     *
     * @param port port on which the driver server should listen for commands
     * @return the created {@link Browser}.
     * @see Browser
     * @see BrowserFactory
     * @since 1.2
     */
    public Browser createBrowser(int port) {

        GeckoDriverService service = GeckoDriverService.createDefaultService();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        setOptionalProxyConfiguration(capabilities);

        return createBrowser(new MarionetteDriver(service, capabilities, port));

    }

    @Override
    public Browser createBrowser(DesiredCapabilities capabilities) {
        return createBrowser(new MarionetteDriver(capabilities));
    }

    @Override
    public Browser createBrowser(WebDriver webDriver) {

        if (!(webDriver instanceof MarionetteDriver)) {
            throw new IllegalArgumentException("Wrong WebDriver class: " + webDriver + " only MarionetteDriver is allowed!");
        }

        return WebDriverBrowser.buildForWebDriver(webDriver);

    }

    private void setOptionalProxyConfiguration(DesiredCapabilities capabilities) {
        if (proxyConfiguration != null) {
            Proxy proxy = new Proxy();
            proxyConfiguration.configureProxy(proxy);
            capabilities.setCapability(CapabilityType.PROXY, proxy);
        }
    }

    @Override
    public MarionetteFactory withProxyConfiguration(ProxyConfiguration configuration) {
        proxyConfiguration = configuration;
        return this;
    }

}
