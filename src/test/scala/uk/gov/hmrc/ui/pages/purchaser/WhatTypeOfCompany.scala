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

  def Bank: String                                          = "value_0"
  def Building_Association: String                          = "value_1"
  def Central_Government: String                            = "value_2"
  def Individual_other_than_sole_trader: String             = "value_3"
  def Insurance_Assurance_company: String                   = "value_4"
  def Local_authority: String                               = "value_5"
  def Partnership: String                                   = "value_6"
  def Property_company: String                              = "value_7"
  def Public_corporation: String                            = "value_8"
  def Other_company: String                                 = "value_9"
  def Other_financial_institute: String                     = "value_10"
  def Other_including_charity: String                       = "value_11"
  def Superannuation_or_pension_fund: String                = "value_12"
  def Unincorporated_builder: String                        = "value_13"
  def Unincorporated_sole_trader_other_than_builder: String = "value_14"

  def selectCompanyTypes(companyTypes: Seq[String]): Unit =
    companyTypes.foreach {
      case "Bank"                                          => clickLinkById(Bank)
      case "Building Association"                          => clickLinkById(Building_Association)
      case "Central Government"                            => clickLinkById(Central_Government)
      case "Individual other than sole trader"             => clickLinkById(Individual_other_than_sole_trader)
      case "Insurance/Assurance company"                   => clickLinkById(Insurance_Assurance_company)
      case "Local authority"                               => clickLinkById(Local_authority)
      case "Partnership"                                   => clickLinkById(Partnership)
      case "Property company"                              => clickLinkById(Property_company)
      case "Public corporation"                            => clickLinkById(Public_corporation)
      case "Other company"                                 => clickLinkById(Other_company)
      case "Other financial institute"                     => clickLinkById(Other_financial_institute)
      case "Other including charity"                       => clickLinkById(Other_including_charity)
      case "Superannuation or pension fund"                => clickLinkById(Superannuation_or_pension_fund)
      case "Unincorporated builder"                        => clickLinkById(Unincorporated_builder)
      case "Unincorporated sole trader other than builder" =>
        clickLinkById(Unincorporated_sole_trader_other_than_builder)
      case other                                           => println(s"Unknown company type: $other")
    }
}
