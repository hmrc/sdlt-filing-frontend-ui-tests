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

object CalculateSDLTDue extends BasePage {

  override def pageUrl: String = "tax-calculation/freehold-calculated/calculated-SDLT-due"

  override def pageTitle: String =
    "Calculated SDLT due - Tax calculation - Freehold calculated - Stamp Taxes Online - GOV.UK"

  def freeholdSelfAssesedSDLTDuepageUrl: String =
    "tax-calculation/freehold-not-calculated/HMRC-cannot-calculate-SDLT-due"

  def freeholdSelfAssesedSDLTDuepageTitle: String =
    "HMRC cannot calculate the SDLT due - Tax calculation - Freehold not calculated - Stamp Taxes Online - GOV.UK"

  def leaseholdSelfAssesedSDLTDuepageUrl: String =
    "tax-calculation/leasehold-not-calculated/HMRC-cannot-calculate-SDLT-due"

  def leaseholdSelfAssesedSDLTDuepageTitle: String =
    "HMRC cannot calculate the SDLT due - Tax calculation - Leasehold not calculated - Stamp Taxes Online - GOV.UK"

  def leaseholdSDLTDuepageUrl: String =
    "tax-calculation/leasehold-calculated/calculated-SDLT-due"

  def leaseholdSDLTDuepageTitle: String =
    "Calculated SDLT due - Tax calculation - Leasehold calculated - Stamp Taxes Online - GOV.UK"

  def clickSDLTBreakDownLink(): Unit =
    click(By.xpath("//a[@class='govuk-body govuk-link' and text()='Check your SDLT breakdown']"))

}
