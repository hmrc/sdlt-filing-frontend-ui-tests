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

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object VendorPropertyAddressPage extends BasePage {

  override def pageUrl: String = "/lookup-address"

  override def pageTitle: String =
    s"Find ${VendorNamePage.companyNameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  val editPageTitleBusiness: String =
    s"Enter ${VendorNamePage.companyNameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  val confirmPageTitleBusiness: String =
    s"Review and confirm ${VendorNamePage.companyNameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  def PageTitleIndividual: String =
    s"Find ${VendorNamePage.forenameInput} ${VendorNamePage.middlenameInput} ${VendorNamePage.surnameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  val editPageTitleIndividual: String =
    s"Enter ${VendorNamePage.forenameInput} ${VendorNamePage.middlenameInput} ${VendorNamePage.surnameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  val confirmPageTitleIndividual: String =
    s"Review and confirm ${VendorNamePage.forenameInput} ${VendorNamePage.middlenameInput} ${VendorNamePage.surnameInput}’s address - About the vendor - Stamp Taxes Online - GOV.UK"

  def enterPostCode(postCode: String): Unit = {
    input(Locators.txtPostCode, postCode)
    clickSubmitButton()
  }

  def clickAddressManually(): Unit = click(By.linkText(Locators.lnkAddrManually))

  def enterAddressManually(addressline1: String, town: String, addressPostcode: String): Unit = {
    input(Locators.txtAddress1, addressline1)
    input(Locators.txtTown, town)
    input(Locators.txtAddressPostCode, addressPostcode)
    clickSubmitButton()
  }
}
