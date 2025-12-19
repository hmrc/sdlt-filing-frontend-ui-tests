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

object WhatTypeOfCompany extends BasePage {

  override def pageUrl: String = "about-the-purchaser/type-of-company"

  override def pageTitle: String =
    "What type of company is the purchaser? – About the purchaser - Stamp Taxes Online - GOV.UK"

  val Bank: String                                          = "#value_0"
  val Building_Association: String                          = "#value_1"
  val Central_Government: String                            = "#value_2"
  val Individual_other_than_sole_trader: String             = "#value_3"
  val Insurance_Assurance_company: String                   = "#value_4"
  val Local_authority: String                               = "#value_5"
  val Partnership: String                                   = "#value_6"
  val Property_company: String                              = "#value_7"
  val Public_corporation: String                            = "#value_8"
  val Other_company: String                                 = "#value_9"
  val Other_financial_institute: String                     = "#value_10"
  val Other_including_charity: String                       = "#value_11"
  val Superannuation_or_pension_fund: String                = "#value_12"
  val Unincorporated_builder: String                        = "#value_13"
  val Unincorporated_sole_trader_other_than_builder: String = "#value_14"

}
