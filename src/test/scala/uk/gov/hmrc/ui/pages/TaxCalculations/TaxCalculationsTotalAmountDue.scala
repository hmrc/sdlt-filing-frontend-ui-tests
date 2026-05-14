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

object TaxCalculationsTotalAmountDue extends BasePage {

  override def pageUrl: String = "tax-calculation/leasehold-not-calculated/total-amount-due"

  override def pageTitle: String =
    "Total amount due - Tax calculation - Leasehold not calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlFreehold: String = "tax-calculation/freehold-calculated/total-amount-due"

  def pageTitleFreehold: String =
    "Total amount due - Tax calculation - Freehold calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlLeasehold: String = "tax-calculation/leasehold-calculated/total-amount-due"

  def pageTitleLeasehold: String =
    "Total amount due - Tax calculation - Leasehold calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlFreeholdSelfAssesedTAD: String = "tax-calculation/freehold-not-calculated/total-amount-due"

  def pageTitleFreeholdSelfAssesedTAD: String =
    "Total amount due - Tax calculation - Freehold not calculated - Stamp Taxes Online - GOV.UK"

  val tppTax: String = "value"

  val tppTaxInput: String = "10000"
}
