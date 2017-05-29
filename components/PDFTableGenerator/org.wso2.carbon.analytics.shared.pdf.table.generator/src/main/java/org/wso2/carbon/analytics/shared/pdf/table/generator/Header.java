/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.analytics.shared.pdf.table.generator;
import org.pdfbox.pdmodel.font.PDFont;

public class Header extends PDFPageInfo {
    //Logo Attributes
    private Point logoCoordinates = DefaultConstants.DEFAULT_LOGO_COORDINATES;
    private Point logoSize = DefaultConstants.DEFAULT_LOGO_SIZE;
    private String logoLocation = DefaultConstants.DEFAULT_LOGO_LOCATION;

    //Title Attributes
    private String title;
    private PDFont titleFont = DefaultConstants.DEFAULT_TITLE_FONT;
    private float titleFontSize = DefaultConstants.DEFAULT_TITLE_FONT_SIZE;
    private Point titleCoordinates = DefaultConstants.DEFAULT_TITLE_COORDINATES;

    //HeaderInfo Attributes
    private String[] headerInfo;
    private PDFont headerInfoFont = DefaultConstants.DEFAULT_HEADER_INFO_FONT;
    private float headerInfoFontSize = DefaultConstants.DEFAULT_HEADER_INFO_FONT_SIZE;
    private Point headerCoordinates = DefaultConstants.DEFAULT_HEADER_COORDINATES;

    /*
     *gets the Point object that contains logo coordinates
     */
    public Point getLogoCoordinates() {
        return logoCoordinates;
    }

    /*
     *gets the Point object that contains logo size
     */
    public Point getLogoSize() {
        return logoSize;
    }

    /*
     *gets the String that contains logo Location
     */
    public String getLogoLocation() {
        return logoLocation;
    }

    /*
     *gets the PDFont object that contains the font of title
     */
    public PDFont getTitleFont() {
        return titleFont;
    }

    /*
     *gets the float of title font size
     */
    public float getTitleFontSize() {
        return titleFontSize;
    }

    /*
     *gets the String that contains the title
     */
    public String getTitle() {
        return title;
    }

    /*
     *gets the Point object that contains the title coordinates
     */
    public Point getTitleCoordinates() {
        return titleCoordinates;
    }

    /*
     *sets the logo image coordinates
     *@param logoCoordinates Point that contains the logo coordinates
     */
    public void setLogoCoordinates(Point logoCoordinates) {
        this.logoCoordinates = logoCoordinates;
    }

    /*
     *sets the logo image coordinates
     *@param logoCoordinates Point that contains the logo coordinates
     */
    public void setLogoSize(Point logoSize) {
        this.logoSize = logoSize;
    }

    /*
     *sets the logo location
     *@param logoLocation String that contains the logo location
     */
    public void setLogoLocation(String logoLocation) {
        this.logoLocation = logoLocation;
    }

    /*
     *sets the title
     *@param title String of the title of the PDF
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /*
     *sets the font of title
     *@param titleFont PDFont object that contains the font of title
     */
    public void setTitleFont(PDFont titleFont) {
        this.titleFont = titleFont;
    }

    /*
     *sets the font size of title
     *@param titleFontSize float of title font size
     */
    public void setTitleFontSize(float titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    /*
     *sets the title coordinates
     *@param titleCoordinates Point object that contains the title coordinates
     */
    public void setTitleCoordinates(Point titleCoordinates) {
        this.titleCoordinates = titleCoordinates;
    }

    /*
     *gets the PDFont object that contains the font of header information
     */
    public PDFont getHeaderInfoFont() {
        return headerInfoFont;
    }

    /*
     *gets the float of header information font size
     */
    public float getHeaderInfoFontSize() {
        return headerInfoFontSize;
    }

    /*
     *gets the String array of object that contains the header information
     */
    public String[] getHeaderInfo() {
        return headerInfo;
    }

    /*
     *gets the Point object that contains the header information coordinates
     */
    public Point getHeaderCoordinates() {
        return headerCoordinates;
    }

    /*
     *sets the header information
     *@param headerInfo String[] that contains the header information
     */
    public void setHeaderInfo(String[] headerInfo) {
        this.headerInfo = headerInfo;
    }

    /*
     *sets the font of header information
     *@param headerInfoFont PDFont object that contains the font of header information
     */
    public void setHeaderInfoFont(PDFont headerInfoFont) {
        this.headerInfoFont = headerInfoFont;
    }

    /*
     *sets the font size of header information
     *@param titleCoordinates Point object that contains the title coordinates
     */
    public void setHeaderInfoFontSize(float headerInfoFontSize) {
        this.headerInfoFontSize = headerInfoFontSize;
    }

    /*
     *sets the coordinates of header information
     *@param headerCoordinates Point object that contains the header information coordinates
     */
    public void setHeaderCoordinates(Point headerCoordinates) {
        this.headerCoordinates = headerCoordinates;
    }
}
