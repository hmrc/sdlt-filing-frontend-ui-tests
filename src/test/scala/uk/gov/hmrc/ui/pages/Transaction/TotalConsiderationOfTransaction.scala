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

object TotalConsiderationOfTransaction extends BasePage {

  override def pageUrl: String = "about-the-transaction/total-consideration-of-transaction"

  override def pageTitle: String =
    "Total consideration of the transaction - About the transaction - Stamp Taxes Online - GOV.UK"

  val totalConsideration: String = "value"

  val totalConsiderationInput: String = "300000"

}
