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
import org.openqa.selenium.By

object EffectiveDateOfTransaction extends BasePage {

  override def pageUrl: String = "about-the-transaction/effective-date-of-transaction"

  override def pageTitle: String =
    "Effective date of the transaction - About the transaction - Stamp Taxes Online - GOV.UK"

  val day: String = "value.day"

  val dayInput: String = "30"

  val dayInputCYA: String = "15"

  val month: String = "value.month"

  val monthInput: String = "03"

  val monthInputCYA: String = "06"

  val year: String = "value.year"

  val yearInput: String = "2004"

  val yearInputCYA: String = "2008"

  def enterEffectiveDateOfTransaction(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInput,
    monthInput,
    yearInput
  )

  def enterEffectiveDateOfTransactionCYA(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInputCYA,
    monthInputCYA,
    yearInputCYA
  )

  def clickDropdownText(): Unit = driver.findElement(By.cssSelector("summary.govuk-details__summary")).click()

  def dropdownText: String =
    "The effective date of transaction can be brought forward if the contract is substantially performed prior to completion. Substantial performance occurs when the purchaser pays 90% or more of the total price, occupies 90% or more of the property, or pays any rent."

}
