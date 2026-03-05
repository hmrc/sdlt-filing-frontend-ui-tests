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

object PurchaserName extends BasePage {

  override def pageUrl: String = "about-the-purchaser/purchaser-name"

  override def pageTitle: String = "What is the purchaser’s name? - About the purchaser - Stamp Taxes Online - GOV.UK"

  def purchaserNameIndividual  = "forename1 forename2 surname"
  def purchaserNameCompany     = "Company Name"
  def purchaserNameCompanyStub = "Stub Company name"

  def purchasersSurname: String = "purchaserSurnameOrCompanyName"
  def forenameId: String        = "forename1"
  def middlenameId: String      = "forename2"
  def surnameId: String         = "name"

  def forenameInput: String   = "forename1"
  def middlenameInput: String = "forename2"
  def surnameInput: String    = "surname"
  def surnameInput2: String   = "surname2"

  def companyName: String  = "purchaserSurnameOrCompanyName"
  def companyName2: String = "Company Name"
  def companyId: String    = "name"
}
