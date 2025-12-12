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

package uk.gov.hmrc.ui.pages.purchaser

import uk.gov.hmrc.ui.pages.BasePage

object VATRegistrationNumberPage extends BasePage {

  override def pageUrl: String = "about-the-purchaser/vat-registration-number"

  override def pageTitle: String =
    "What is the purchaser’s VAT registration number? - About the purchaser - Stamp Taxes Online - GOV.UK"

  def vat: String = "registrationNumber"

  def VATNumber: String = "123456782"
}
