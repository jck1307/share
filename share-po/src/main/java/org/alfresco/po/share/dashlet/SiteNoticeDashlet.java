/*
 * #%L
 * share-po
 * %%
 * Copyright (C) 2005 - 2016 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software. 
 * If the software was purchased under a paid Alfresco license, the terms of 
 * the paid license agreement will prevail.  Otherwise, the software is 
 * provided under the following open source license terms:
 * 
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.po.share.dashlet;

import java.util.List;

import org.alfresco.po.RenderTime;
import org.alfresco.po.RenderWebElement;
import org.alfresco.po.exception.PageException;
import org.alfresco.po.exception.PageOperationException;
import org.alfresco.po.share.ShareLink;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
@FindBy(css="div.dashlet.notice-dashlet")
/**
 * Site Notice dashlet object, holds all element of the HTML relating to site Notice dashlet.
 * 
 * @author Chiran
 */
public class SiteNoticeDashlet extends AbstractDashlet implements Dashlet
{
    private static Log logger = LogFactory.getLog(SiteNoticeDashlet.class);
    @RenderWebElement
    private static final By DASHLET_CONTAINER_PLACEHOLDER = By.cssSelector("div.dashlet.notice-dashlet");
    @RenderWebElement
    private static final By HELP_ICON = By.cssSelector("div.dashlet.notice-dashlet div.titleBarActionIcon.help");
    @RenderWebElement
    private static final By CONFIGURE_ICON = By.cssSelector("div.dashlet.notice-dashlet div.titleBarActionIcon.edit");
    @RenderWebElement
    private static final By DASHLET_CONTENT = By.cssSelector("div[id$='default-text']");
    @RenderWebElement
    private static final By DASHLET_TITLE = By.cssSelector("div[id$='default-title']");
    private static final By DASHLET_HELP_BALLOON = By.cssSelector("div[style*='visible']>div.bd>div.balloon");
    private static final By DASHLET_HELP_BALLOON_TEXT = By.cssSelector("div[style*='visible']>div.bd>div.balloon>div.text");
    private static final By DASHLET_HELP_BALLOON_CLOSE_BUTTON = By.cssSelector("div[style*='visible']>div.bd>div.balloon>div.closeButton");

//    /**
//     * Constructor.
//     */
//    protected SiteNoticeDashlet(WebDriver driver)
//    {
//        super(driver, DASHLET_CONTAINER_PLACEHOLDER);
//        setResizeHandle(By.cssSelector("div.dashlet.notice-dashlet .yui-resize-handle"));
//    }
    @SuppressWarnings("unchecked")
    public SiteNoticeDashlet render(RenderTime timer)
    {
        setResizeHandle(By.cssSelector("div.dashlet.notice-dashlet .yui-resize-handle"));
        scrollDownToDashlet();
        getFocus();
        webElementRender(timer);
        return this;
    }

    /**
     * Finds whether help icon is displayed or not.
     * 
     * @return True if the help icon displayed else false.
     */
    public boolean isHelpIconDisplayed()
    {
        try
        {
            scrollDownToDashlet();
            getFocus();
            return findAndWait(HELP_ICON).isDisplayed();
        }
        catch (TimeoutException te)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Unable to find the help icon.", te);
            }
        }

        return false;
    }

    /**
     * Finds whether configure icon is displayed or not.
     * 
     * @return True if the configure icon displayed else false.
     */
    public boolean isConfigureIconDisplayed()
    {
        try
        {
            scrollDownToDashlet();
            getFocus();
            return findAndWait(CONFIGURE_ICON).isDisplayed();
        }
        catch (TimeoutException te)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Unable to find the configure icon.", te);
            }
        }

        return false;
    }

    /**
     * This method is used to Finds Help icon and clicks on it.
     */
    public void clickOnHelpIcon()
    {
        try
        {
            scrollDownToDashlet();
            getFocus();
            findAndWait(HELP_ICON).click();
        }
        catch (TimeoutException te)
        {
            if (logger.isTraceEnabled())
            {
                logger.info("Unable to find the help icon.", te);
            }
            throw new PageOperationException("Unable to click the Help icon", te);
        }
    }

    /**
     * This method is used to Finds Configure icon and clicks on it.
     * 
     * @return {@link ConfigureSiteNoticeDialogBoxPage}
     */
    public ConfigureSiteNoticeDialogBoxPage clickOnConfigureIcon()
    {
        try
        {
            scrollDownToDashlet();
            getFocus();
            findAndWait(CONFIGURE_ICON).click();
            return getCurrentPage().render();
        }
        catch (TimeoutException te)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Unable to find the help icon.", te);
            }
        }

        throw new PageOperationException("Unable to click the Configure icon");
    }

    /**
     * Finds whether help balloon is displayed on this page.
     * 
     * @return True if the balloon displayed else false.
     */
    public boolean isBalloonDisplayed()
    {
        try
        {
            return findAndWait(DASHLET_HELP_BALLOON).isDisplayed();
        }
        catch (TimeoutException elementException)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Exceeded time to find the ballon", elementException);
            }
        }
        return false;
    }

    /**
     * This method gets the Help balloon messages and merge the message into string.
     * 
     * @return String
     */
    public String getHelpBalloonMessage()
    {
        try
        {
            return findAndWait(DASHLET_HELP_BALLOON_TEXT).getText();
        }
        catch (TimeoutException elementException)
        {
            if (logger.isTraceEnabled())
            {
                logger.trace("Exceeded time to find the help ballon text", elementException);
            }
        }

        throw new UnsupportedOperationException("Not able to find the help text");
    }

    /**
     * This method closes the Help balloon message.
     */
    public SiteNoticeDashlet closeHelpBallon()
    {
        try
        {
            findAndWait(DASHLET_HELP_BALLOON_CLOSE_BUTTON).click();
            return this;
        }
        catch (TimeoutException elementException)
        {
            throw new UnsupportedOperationException("Exceeded time to find the help ballon close button.", elementException);
        }
    }

    /**
     * This method gets the SiteNotice Dashlet title.
     * 
     * @return String
     */
    public String getTitle()
    {
        try
        {
            scrollDownToDashlet();
            getFocus();
            return findAndWait(DASHLET_TITLE).getText();
        }
        catch (TimeoutException te)
        {
            throw new UnsupportedOperationException("Exceeded time to find the title.", te);
        }
    }

    /**
     * This method gets the SiteNotice Dashlet content.
     * 
     * @return String
     */
    public String getContent()
    {
        try
        {
            scrollDownToDashlet();
            getFocus();
            return findAndWait(DASHLET_CONTENT).getText();
        }
        catch (TimeoutException te)
        {
            throw new UnsupportedOperationException("Exceeded time to find the title.", te);
        }
    }

    /**
     * This method gets the focus by placing mouse over on Site Content Dashlet.
     */
    protected void getFocus()
    {
        mouseOver(findAndWait(DASHLET_CONTAINER_PLACEHOLDER));
    }
 
    /**
     * Retrieves the link that match the content name.
     * 
     * @param name
     *            identifier
     */
    public void selectLink(final String name)
    {
        if(name == null)
        {
            throw new UnsupportedOperationException("Name value of link is required");
        }
        List<ShareLink> shareLinks = getList("div.notice-dashlet div.text-content>p>a");
        if(shareLinks != null)
        {
            for (ShareLink link : shareLinks)
            {
                if (name.equalsIgnoreCase(link.getDescription()))
                {
                    link.click();
                    return;
                }
            }
            throw new PageException(String.format("Link %s can not be found on the page, dashlet exists: %s link size: %d",
                    name,
                    dashlet,
                    shareLinks.size()));
        }
        throw new PageException("Link can not be found on the dashlet");
    }
    @SuppressWarnings("unchecked")
    @Override
    public SiteNoticeDashlet render()
    {
        return render(new RenderTime(maxPageLoadingTime));
    }
}
