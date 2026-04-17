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

object FormsOfConsideration extends BasePage {
  override def pageUrl: String   = "about-the-transaction/forms-of-consideration"
  override def pageTitle: String =
    "Forms of consideration - About the transaction - Stamp Taxes Online - GOV.UK"

  val cash: String = "#value_0"

  val debt: String = "#value_1"

  val building_works: String = "#value_2"

  val employment: String = "#value_3"

  val other: String = "#value_4"

  val shares_quoted_company: String = "#value_5"

  val shares_unquoted_company: String = "#value_6"

  val other_land: String = "#value_7"

  val services: String = "#value_8"

  val contingent: String = "#value_9"

}
