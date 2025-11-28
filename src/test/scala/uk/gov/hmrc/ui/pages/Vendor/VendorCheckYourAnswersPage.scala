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

object VendorCheckYourAnswersPage extends BasePage {

  override def pageUrl: String = "about-the-vendor/check-answers"

  override def pageTitle: String =
    "Check your answers – About the vendor - Stamp Taxes Online - GOV.UK"

  val vendorTypeChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendor/who-is-the-vendor/change']"

  val vendorNameChange = "a[href ='/stamp-duty-land-tax-filing/about-the-vendor/vendor-name/change']"

  val vendorAddressChange = "a[href ='/stamp-duty-land-tax-filing/about-the-vendor/address?changeRoute=change']"

  def clickVendorNameChange(): Unit = click(By.cssSelector(vendorNameChange))

  def clickVendorTypeChange(): Unit = click(By.cssSelector(vendorTypeChange))

  def clickVendorAddressChange(): Unit = click(By.cssSelector(vendorAddressChange))

}
