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

import java.util.List;

public class Table extends PDFPageInfo {

    private PDFont textFont = DefaultConstants.DEFAULT_TEXT_FONT;
    private float fontSize = DefaultConstants.DEFAULT_FONT_SIZE;
    private float cellMargin = DefaultConstants.DEFAULT_CELL_MARGIN;
    private float rowHeight = DefaultConstants.DEFAULT_ROW_HEIGHT;
    private float tableTopY = DefaultConstants.DEFAULT_TABLE_TOP_Y;
    private List<Column> columns;
    private String[][] content;

    private PDFont tableHeaderFont = DefaultConstants.DEFAULT_TABLE_HEADER_FONT;
    private float tableHeaderFontSize = DefaultConstants.DEFAULT_TABLE_HEADER_FONT_SIZE;
    private Color tableHeaderBackgroundColor = DefaultConstants.DEFAULT_TABLE_HEADER_BACKGROUND_COLOR;
    private Color tableFontColor = DefaultConstants.DEFAULT_TABLE_FONT_COLOR;
    private Color alternativeRowColor = DefaultConstants.DEFAULT_ALTERNATIVE_ROW_COLOR;
    private Color tableBodyFillColor = DefaultConstants.DEFAULT_TABLE_BODY_FILL_COLOR;

    /*
     *gets the float of total width of the table
     */
    public float getTableWidth() {

        float tableWidth = 0f;
        for (Column column : columns) {
            tableWidth += column.getWidth();
        }
        return tableWidth;
    }

    /*
     *gets the float of top y coordinate of the table
     */
    public float getTableTopY() {
        return tableTopY;
    }

    /*
     *gets the String[] of column names
     */
    public String[] getColumnsNamesAsArray() {

        String[] columnNames = new String[getNumberOfColumns()];
        for (int i = 0; i < getNumberOfColumns(); i++) {
            columnNames[i] = columns.get(i).getName();
        }
        return columnNames;
    }

    /*
     *gets the List of columns that contains columns object
     */
    public List<Column> getColumns() {
        return columns;
    }

    /*
     *gets the number of columns
     */
    public Integer getNumberOfColumns() {
        return this.getColumns().size();
    }

    /*
     *sets the cell margin
     *@param cellMargin float of the cell margin
     */
    public void setCellMargin(float cellMargin) {
        this.cellMargin = cellMargin;
    }

    /*
     *sets the columns
     *@param columns list of Columns objects
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /*
     *sets the contents
     *@param content String[][] of content
     */
    public void setContent(String[][] content) {
        this.content = content;
    }

    /*
     *sets the row height
     *@param rowHeight float of row height
     */
    public void setRowHeight(float rowHeight) {
        this.rowHeight = rowHeight;
    }

    /*
     *sets the font of the text
     *@param textFont PDFont object of text Font
     */
    public void setTextFont(PDFont textFont) {
        this.textFont = textFont;
    }

    /*
     *sets the font size of the text of the table body
     *@param fontSize float of font size
     */
    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    /*
     *sets the top y coordinate of the table
     *@param tableTopY float of y coordinate
     */
    public void setTableTopY(float tableTopY) {
        this.tableTopY = tableTopY;
    }

    /*
     *gets the float of cell margin
     */
    public float getCellMargin() {
        return cellMargin;
    }

    /*
     *gets the float of row height
     */
    public float getRowHeight() {
        return rowHeight;
    }

    /*
     *gets the PDFont object that contains the font of the text
     */
    public PDFont getTextFont() {
        return textFont;
    }

    /*
     *gets the float of font size
     */
    public float getTextFontSize() {
        return fontSize;
    }

    /*
     *gets the String[][] of table content
     */
    public String[][] getContent() {
        return content;
    }

    /*
     *sets the table header font
     *@param tableHeaderFont PDFont object of table header font
     */
    public void setTableHeaderFont(PDFont tableHeaderFont) {
        this.tableHeaderFont = tableHeaderFont;
    }

    /*
     *sets the table header font size
     *@param tableHeaderFont float of table header font size
     */
    public void setTableHeaderFontSize(float tableHeaderFontSize) {
        this.tableHeaderFontSize = tableHeaderFontSize;
    }

    /*
     *gets the float of table header font size
     */
    public float getTableHeaderFontSize() {
        return tableHeaderFontSize;
    }

    /*
     *gets the PDFont object of table header font
     */
    public PDFont getTableHeaderFont() {
        return tableHeaderFont;
    }

    /*
     *sets the table header background color
     *@param tableHeaderBackgroundColor Color object of table header background color
     */
    public void setTableHeaderBackgroundColor(Color tableHeaderBackgroundColor) {
        this.tableHeaderBackgroundColor = tableHeaderBackgroundColor;
    }

    /*
     *gets the Color object of table header background color
     */
    public Color getTableHeaderBackgroundColor() {
        return tableHeaderBackgroundColor;
    }

    /*
     *sets the table font color
     *@param tableFontColor Color object of table font color
     */
    public void setTableFontColor(Color tableFontColor) {
        this.tableFontColor = tableFontColor;
    }

    /*
     *gets the Color object of table font color
     */
    public Color getTableFontColor() {
        return tableFontColor;
    }

    /*
     *sets the table alternative row background color
     *@param alternativeRowColor Color object of table alternative row background color
     */
    public void setAlternativeRowColor(Color alternativeRowColor) {
        this.alternativeRowColor = alternativeRowColor;
    }

    /*
     *gets the Color object of table alternative row background color
     */
    public Color getAlternativeRowColor() {
        return alternativeRowColor;
    }

    /*
     *sets the table body fill color
     *@param tableBodyFillColor Color object of table body fill color
     */
    public void setTableBodyFillColor(Color tableBodyFillColor) {
        this.tableBodyFillColor = tableBodyFillColor;
    }

    /*
     *gets the Color object of table body background color
     */
    public Color getTableBodyFillColor() {
        return tableBodyFillColor;
    }
}
