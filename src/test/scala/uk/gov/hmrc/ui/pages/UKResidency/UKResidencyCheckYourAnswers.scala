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

package uk.gov.hmrc.ui.pages.UKResidency

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object UKResidencyCheckYourAnswers extends BasePage {

  override def pageUrl: String   = "about-UK-residency/check-answers"
  override def pageTitle: String = "Check your answers - About UK residency - Stamp Taxes Online - GOV.UK"

  val residencyStatusChange = "a[href ='/stamp-duty-land-tax-filing/about-UK-residency/residency-status/change']"

  val crownEmploymentReliefChange =
    "a[href ='/stamp-duty-land-tax-filing/about-UK-residency/crown-employment-relief/change']"

  val closeCompaniesChange = "a[href ='/stamp-duty-land-tax-filing/about-UK-residency/close-companies/change']"

  def clickResidencyStatusChange(): Unit = click(By.cssSelector(residencyStatusChange))

  def clickCrownEmploymentReliefChange(): Unit = click(By.cssSelector(crownEmploymentReliefChange))

  def clickCloseCompaniesChange(): Unit = click(By.cssSelector(closeCompaniesChange))

}
