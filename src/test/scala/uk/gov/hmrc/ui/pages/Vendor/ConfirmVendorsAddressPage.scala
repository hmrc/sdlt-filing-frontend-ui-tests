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

object ConfirmVendorsAddressPage extends BasePage {

  override def pageUrl: String = "about-the-vendor/confirm-vendor-address"

  override def pageTitle: String =
    s"Confirm ${VendorOrCompanyNamePage.companyNameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  def pageTitleIndividual: String =
    s"Confirm ${VendorOrCompanyNamePage.forenameInput} ${VendorOrCompanyNamePage.middlenameInput} ${VendorOrCompanyNamePage.surnameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  def yes: String = "#value_0"

  def no: String = "#value_1"
}
