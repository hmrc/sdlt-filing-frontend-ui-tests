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

import uk.gov.hmrc.ui.pages.BasePage
import org.openqa.selenium.{By, WebDriver}

object DoYouKnowCharityRegistrationNumber extends BasePage {

  override def pageUrl: String = "about-the-transaction/add-registered-charity-number"

  override def pageTitle: String =
    "Registered charity number - About the transaction - Stamp Taxes Online - GOV.UK"

  val yes: String = "#value"

  val no: String = "#value-2"

  def clickCharityCommisionLink(): Unit                                               = click(
    By.xpath("//a[contains(@href,'register-of-charities.charitycommission')]")
  )
  def validateCharityComissionLink()(implicit driver: WebDriver): Unit                = {
    clickCharityCommisionLink()
    switchToNewTabAndValidateTitle("About the register of charities")
  }
  def clickCharityCommisionNorthernIrelandLink(): Unit                                = click(
    By.xpath("//a[contains(@href,'charitycommissionni.org.uk/charity-search')]")
  )
  def validateCharityComissionNorthernIrelandLink()(implicit driver: WebDriver): Unit = {
    clickCharityCommisionNorthernIrelandLink()
    switchToNewTabAndValidateTitle("Charity search")
  }
}
