/*
 * Copyright 2026 HM Revenue & Customs
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

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object TaxCalculationCheckYourAnswers extends BasePage {

  override def pageUrl: String =
    "tax-calculation/check-answers"

  override def pageTitle: String =
    "Check your answers - Tax calculation - Stamp Taxes Online - GOV.UK"

  val selfAssessedSDLTAmountChange =
    "a[href='/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/SDLT-self-assessment/change']"

  val amountTobePaidChange =
    "a[href='/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/total-amount-due/change']"

  val penaltiesChange =
    "a[href='/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/are-penalties-and-interest-included/change']"

  def clickselfAssessedSDLTAmountChange(): Unit =
    click(By.cssSelector(selfAssessedSDLTAmountChange))

  def clickamountTobePaidChange(): Unit =
    click(By.cssSelector(amountTobePaidChange))

  def clickpenaltiesChange(): Unit =
    click(By.cssSelector(penaltiesChange))
}
