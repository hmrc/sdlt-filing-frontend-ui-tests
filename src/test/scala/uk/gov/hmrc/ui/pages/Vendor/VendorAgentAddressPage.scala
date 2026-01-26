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
import uk.gov.hmrc.ui.pages.VendorAgent.VendorAgentsNamePage

object VendorAgentAddressPage extends BasePage {

  override def pageUrl: String = "/lookup-address"

  override def pageTitle: String =
    s"Find ${VendorAgentsNamePage.agentNameInput}’s address - About the Vendor - Stamp Taxes Online - GOV.UK"

  val editPageTitleAgent: String =
    s"Enter ${VendorAgentsNamePage.agentNameInput}’s address - About the Vendor - Stamp Taxes Online - GOV.UK"

  val confirmPageTitleAgent: String =
    s"Review and confirm ${VendorAgentsNamePage.agentNameInput}’s address - About the Vendor - Stamp Taxes Online - GOV.UK"

  def clickAddressManually(): Unit = click(By.linkText(Locators.lnkAddrManually))

  def enterAddressManually(addressline1: String, town: String, addressPostcode: String): Unit = {
    input(Locators.txtAddress1, addressline1)
    input(Locators.txtTown, town)
    input(Locators.txtAddressPostCode, addressPostcode)
    clickSubmitButton()
  }
}
