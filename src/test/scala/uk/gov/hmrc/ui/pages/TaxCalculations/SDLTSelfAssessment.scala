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

object SDLTSelfAssessment extends BasePage {

  override def pageUrl: String =
    "tax-calculation/freehold-not-calculated/SDLT-self-assessment"

  override def pageTitle: String =
    "SDLT self-assessment - Tax calculation - Freehold not calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlFreeholdSelfAssesed: String   =
    "tax-calculation/freehold-not-calculated/SDLT-self-assessment"
  def pageTitleFreeholdSelfAssesed: String =
    "SDLT self-assessment - Tax calculation - Freehold not calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlFreeholdTax: String    =
    "tax-calculation/freehold-calculated/SDLT-self-assessment"
  def pageTitleFreeholdTax: String  =
    "SDLT self-assessment - Tax calculation - Freehold calculated - Stamp Taxes Online - GOV.UK"
  def pageUrlLeaseholdTax: String   =
    "tax-calculation/leasehold-calculated/SDLT-self-assessment"
  def pageTitleLeaseholdTax: String =
    "SDLT self-assessment - Tax calculation - Leasehold calculated - Stamp Taxes Online - GOV.UK"

  val saaValue: String = "value"

  val saaInput: String = "10000"
}
