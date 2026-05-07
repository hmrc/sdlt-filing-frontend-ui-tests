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

package uk.gov.hmrc.ui.pages.Transaction

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object TransactionExchangeAddress extends BasePage {

  override def pageUrl: String = "about-the-transaction/transaction-address-lookup"

  override def pageTitle: String =
    "Find the address of the land or property being exchanged or part exchanged - About the transaction - Stamp Taxes Online - GOV.UK"

  val editPageTitle: String =
    "Enter the address of the land or property being exchanged or part exchanged - About the transaction - Stamp Taxes Online - GOV.UK"

  val confirmPageTitle: String =
    "Review and confirm the address of the land or property being exchanged or part exchanged- About the transaction - Stamp Taxes Online - GOV.UK"

  def clickAddressManually(): Unit = click(By.linkText(Locators.lnkAddrManually))

  def enterAddressManually(addressline1: String, town: String, addressPostcode: String): Unit = {
    input(Locators.txtAddress1, addressline1)
    input(Locators.txtTown, town)
    input(Locators.txtAddressPostCode, addressPostcode)
    clickSubmitButton()
  }
}
