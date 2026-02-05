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

package uk.gov.hmrc.ui.pages.Vendor

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object VendorOverviewPage extends BasePage {

  override def pageUrl: String = "stamp-duty-land-tax-filing/about-the-vendor/who-is-the-vendor"

  override def pageTitle: String = "Vendor Overview - About the vendor - Stamp Taxes Online - GOV.UK"

  def yes: String = "#value"

  def no: String = "#value-no"

  val VendorRemoveLink: String =
    "//a[@href='/stamp-duty-land-tax-filing/about-the-vendor/vendor-overview/remove-vendor/VEN-REF-001']"

  def clickRemoveVendor(): Unit = click(By.xpath(VendorRemoveLink))
}
