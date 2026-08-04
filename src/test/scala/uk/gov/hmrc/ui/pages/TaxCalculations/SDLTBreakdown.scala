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

package uk.gov.hmrc.ui.pages.TaxCalculations

import uk.gov.hmrc.ui.pages.BasePage

import org.openqa.selenium.By

object SDLTBreakdown extends BasePage {

  override def pageUrl: String = "tax-calculation/freehold-calculated/SDLT-breakdown"

  override def pageTitle: String =
    "SDLT breakdown - Tax calculation - Freehold calculated - Stamp Taxes Online - GOV.UK"

  def leaseholdSDLTBreakdownpageUrl: String = "tax-calculation/leasehold-calculated/SDLT-breakdown"

  def leaseholdSDLTBreakdownpageTitle: String =
    "SDLT breakdown - Tax calculation - Leasehold calculated - Stamp Taxes Online - GOV.UK"

  val returnTaxCalc = "[data-module='hmrc-back-link']"

  def clickReturnTaxPage(): Unit =
    click(By.xpath("//a[text()='Return to tax calculation']"))

}
