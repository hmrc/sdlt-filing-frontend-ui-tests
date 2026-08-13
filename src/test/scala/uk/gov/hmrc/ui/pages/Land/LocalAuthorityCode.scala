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

package uk.gov.hmrc.ui.pages.Land

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage
import org.openqa.selenium.WebDriver

object LocalAuthorityCode extends BasePage {

  override def pageUrl: String = "about-the-land/local-authority-code"

  override def pageTitle: String =
    "Local authority code - About the land - Stamp Taxes Online - GOV.UK"

  def clickLocalAuthCodeLink(): Unit = click(By.xpath("//a[contains(@href,'sdltm62320')]"))
  def clickDropdownText(): Unit      = driver.findElement(By.cssSelector("summary.govuk-details__summary")).click()

  def clickLandAndBuildingTransaction(): Unit = click(By.xpath("//a[contains(@href,'land-buildings-transaction-tax')]"))
  def clickOneOfSpecialCases(): Unit          = click(
    By.xpath("//a[contains(@href,'transitional-land-and-building-transaction-tax-guidance')]")
  )
  def clickLandTransactionTax(): Unit         = click(By.xpath("//a[contains(@href,'land-transaction-tax-overview')]"))
  def clickOneOfSpecialCasesWales(): Unit     = click(
    By.xpath("//a[contains(@href,'stamp-duty-land-tax-welsh-transactions')]")
  )

  val localAuthCode: String           = "value"
  val localAuthCodeInput: String      = "4215"
  val welshLocalAuthCodeInput: String = "6805"

  def validateListOfLocalAuthCodeLink()(implicit driver: WebDriver): Unit = {
    clickLocalAuthCodeLink()
    switchToNewTabAndValidateTitle("SDLTM62320")
  }

  def validateLandAndBuildingTransactionLink()(implicit driver: WebDriver): Unit = {
    clickLandAndBuildingTransaction()
    switchToNewTabAndValidateTitle("Land and Buildings Transaction Tax")
  }

  def validateOneOfSpecialCasesLink()(implicit driver: WebDriver): Unit = {
    clickOneOfSpecialCases()
    switchToNewTabAndValidateTitle("SDLT to Land and Buildings Transaction Tax")
  }

  def validateLandTransactionTaxLink()(implicit driver: WebDriver): Unit = {
    clickLandTransactionTax()
    switchToNewTabAndValidateTitle("Land Transaction Tax")
  }

  def validateOneOfSpecialCasesWalesLink()(implicit driver: WebDriver): Unit = {
    clickOneOfSpecialCasesWales()
    switchToNewTabAndValidateTitle("Stamp Duty Land Tax: Welsh transactions")
  }

}
