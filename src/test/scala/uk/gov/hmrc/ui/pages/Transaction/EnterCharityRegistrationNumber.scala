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

object EnterCharityRegistrationNumber extends BasePage {

  override def pageUrl: String = "about-the-transaction/enter-registered-charity-number"

  override def pageTitle: String =
    "What is the charity’s registered number? - About the transaction - Stamp Taxes Online - GOV.UK"

  val charityRegistrationNumber: String = "value"

  val charityRegistrationNumberInput: String = "GB104956"

}
