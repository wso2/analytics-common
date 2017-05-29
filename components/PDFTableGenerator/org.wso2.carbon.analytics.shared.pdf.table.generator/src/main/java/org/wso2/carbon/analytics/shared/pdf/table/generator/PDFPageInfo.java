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
import org.pdfbox.pdmodel.common.PDRectangle;

public class PDFPageInfo {

    protected float margin = DefaultConstants.DEFAULT_MARGIN;
    protected PDRectangle pageSize = DefaultConstants.DEFAULT_PAGE_SIZE;

    /*
     *sets the margin of the pdf page
     *@param margin float of the PDF page margin
     */
    public void setMargin(float margin) {
        this.margin = margin;
    }

    /*
     *sets the size of the pdf page
     *@param pageSize PDRectangle object that contains the pdf page size
     */
    public void setPageSize(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    /*
     *gets the float of pdf page margin
     */
    public float getMargin() {
        return margin;
    }

    /*
     *gets the PDRectangle object that contains the size of the pdf Page
     */
    public PDRectangle getPageSize() {
        return pageSize;
    }
}
