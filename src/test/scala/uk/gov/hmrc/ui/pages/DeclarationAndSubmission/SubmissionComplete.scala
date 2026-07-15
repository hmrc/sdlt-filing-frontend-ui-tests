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

package uk.gov.hmrc.ui.pages.DeclarationAndSubmission

import uk.gov.hmrc.ui.pages.BasePage
import org.openqa.selenium.By

object SubmissionComplete extends BasePage {

  override def pageUrl: String = "submit-your-return/submission-complete"

  override def pageTitle: String =
    "Submission complete - Submit your return - Stamp Taxes Online - GOV.UK"

  val submittedReturnLink: By = By.xpath("//a[contains(text(),'View your submitted return')]")

  val sdlt5certificateLink: By = By.xpath("//a[contains(text(),'save your SDLT 5 certificate (opens in new tab)')]")
}
