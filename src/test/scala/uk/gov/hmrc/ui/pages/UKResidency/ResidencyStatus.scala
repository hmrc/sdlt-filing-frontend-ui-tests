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

import uk.gov.hmrc.ui.pages.BasePage
import org.openqa.selenium.{By, WebDriver}

object ResidencyStatus extends BasePage {

  override def pageUrl: String = "about-UK-residency/residency-status"

  override def pageTitle: String =
    "Residency status - About UK residency - Stamp Taxes Online - GOV.UK"

  val yes: String = "#value"

  val no: String = "#value-2"

  def clickResidencyStatusLink(): Unit = click(
    By.xpath("//a[contains(@href,'rates-of-stamp-duty-land-tax-for-non-uk-residents')]")
  )

  def validateListOfResidencyStatusLink()(implicit driver: WebDriver): Unit = {
    clickResidencyStatusLink()
    switchToNewTabAndValidateTitle("rates-of-stamp-duty-land-tax-for-non-uk-residents")
  }
}
