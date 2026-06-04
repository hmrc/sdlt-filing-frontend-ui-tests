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

object EndOfAnnualStartingRent extends BasePage {

  override def pageUrl: String = "about-the-lease/end-of-annual-starting-rent"

  override def pageTitle: String =
    "End of annual starting rent - About the lease - Stamp Taxes Online - GOV.UK"

  val day: String = "value.day"

  val dayInput: String = "30"

  val dayInputCYA: String = "07"

  val month: String = "value.month"

  val monthInput: String = "07"

  val monthInputCYA: String = "08"

  val year: String = "value.year"

  val yearInput: String = "2005"

  val yearInputCYA: String = "2005"

  def enterEndOfAnnualStartingRent(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInput,
    monthInput,
    yearInput
  )

  def enterEndOfAnnualStartingRentCYA(): Unit = inputDateMonthAndYear(
    By.id(day),
    By.id(month),
    By.id(year),
    dayInputCYA,
    monthInputCYA,
    yearInputCYA
  )

}
