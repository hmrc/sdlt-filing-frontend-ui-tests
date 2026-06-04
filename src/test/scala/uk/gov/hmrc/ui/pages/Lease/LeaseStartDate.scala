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

package uk.gov.hmrc.ui.pages.Lease

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object LeaseStartDate extends BasePage {

  override def pageUrl: String = "about-the-lease/lease-start-date"

  override def pageTitle: String =
    "Lease start date - About the lease - Stamp Taxes Online - GOV.UK"

  val day: String = "value.day"

  val dayInput: String = "30"

  val dayInputCYA: String = "29"

  val month: String = "value.month"

  val monthInput: String = "03"

  val monthInputCYA: String = "02"

  val year: String = "value.year"

  val yearInput: String = "2004"

  val yearInputCYA: String = "2004"

  def enterLeaseStartDate(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInput,
    monthInput,
    yearInput
  )

  def enterLeaseStartDateCYA(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInputCYA,
    monthInputCYA,
    yearInputCYA
  )

}
