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

object UseOfLandOrProperty extends BasePage {

  override def pageUrl: String = "about-the-transaction/use-of-land-or-property"

  override def pageTitle: String =
    "The use of the land or property - About the transaction - Stamp Taxes Online - GOV.UK"

  def office              = "#value_0"
  def hotel               = "#value_1"
  def shop                = "#value_2"
  def warehouse           = "#value_3"
  def factory             = "#value_4"
  def otherIndustrialUnit = "#value_5"
  def other               = "#value_6"

}
