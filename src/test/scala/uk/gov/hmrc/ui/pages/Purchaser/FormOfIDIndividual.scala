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

package uk.gov.hmrc.ui.pages.Purchaser

import uk.gov.hmrc.ui.pages.BasePage

object FormOfIDIndividual extends BasePage {

  override def pageUrl: String = "about-the-purchaser/form-of-id-individual"

  override def pageTitle: String =
    "Provide a form of ID for the purchaser - About the purchaser - Stamp Taxes Online - GOV.UK"

  def purchaserIdNumberOrReference: String = "idNumberOrReference"

  def idNumberOrReferenceInput: String = "1234567890"

  def idNumberOrReferenceInput2: String = "UPDATEDID22345"

  def purchaserCountryIssued: String = "countryIssued"

  def countryIssuedInput: String = "country"

}
