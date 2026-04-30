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

object TaxCalculationsBeforeYouStart extends BasePage {

  override def pageUrl: String = "tax-calculation/freehold-calculated/before-you-start"

  override def pageTitle: String =
    "Before you start - Tax calculation - Freehold calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlFreeholdNotCalculated: String = "tax-calculation/freehold-not-calculated/before-you-start"

  def pageTitleFreeholdNotCalculated: String =
    "Before you start - Tax calculation - Freehold not calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlLeaseholdCalculated: String = "tax-calculation/leasehold-calculated/before-you-start"

  def pageTitleLeaseholdCalculated: String =
    "Before you start - Tax calculation - Leasehold calculated - Stamp Taxes Online - GOV.UK"

  def pageUrlLeaseholdNotCalculated: String = "tax-calculation/leasehold-not-calculated/before-you-start"

  def pageTitleLeaseholdNotCalculated: String =
    "Before you start - Tax calculation - Leasehold not calculated - Stamp Taxes Online - GOV.UK"

}
