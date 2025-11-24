/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages.Vendor

import uk.gov.hmrc.ui.pages.BasePage

object VendorOrCompanyNamePage extends BasePage {

  override def pageUrl: String = "about-the-vendor/vendor-name"

  override def pageTitle: String = "What is the vendor’s full name? – About the vendor - Stamp Taxes Online - GOV.UK"

  def pageTitleCompany: String = "What is the company’s name? – About the vendor - Stamp Taxes Online - GOV.UK"

  def forenameInput: String = "forename1"

  def forename: String = "Test firstName"

  def middlename: String = "MiddleName"

  def middlenameInput: String = "forename2"

  def surname: String = "Test surname"

  def surnameInput: String = "name"

  def companyNameInput: String = "name"

  def companyName: String = "test Business"
}
