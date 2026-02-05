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

object PurchaserRemovePage extends BasePage {

  override def pageUrl: String = "about-the-purchaser/remove-purchaser"

  override def pageTitle: String =
    "Are you sure you want to remove the purchaser? – About the purchaser - Stamp Taxes Online - GOV.UK"

  def yes: String = "#value_0"

  def no: String = "#value_1"
}
