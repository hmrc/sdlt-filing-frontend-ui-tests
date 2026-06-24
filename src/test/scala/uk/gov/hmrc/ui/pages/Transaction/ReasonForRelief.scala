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

package uk.gov.hmrc.ui.pages.Transaction

import uk.gov.hmrc.ui.pages.BasePage

object ReasonForRelief extends BasePage {

  override def pageUrl: String = "about-the-transaction/reason-for-relief"

  override def pageTitle: String =
    "What is the reason for claiming relief? - About the transaction - Stamp Taxes Online - GOV.UK"

  val partExchange: String                   = "#value_0"
  val relocationOfEmployment: String         = "#value_1"
  val compulsoryDevelopment: String          = "##value_2"
  val compliancePlanning: String             = "#value_3"
  val groupRelief: String                    = "##value_4"
  val reconstructionRelief: String           = "#value_5"
  val acquisitionReliefTax: String           = "#value_6"
  val demutualisationInsurance: String       = "#value_7"
  val demutualisationBuildingSociety: String = "#value_8"
  val incorporationLimitedLiability: String  = "#value_9"
  val transfersPublic: String                = "#value_10"
  val transfersReorganisation: String        = "#value_11"
  val charitiesRelief: String                = "#value_12"
  val acquisitionByBodies: String            = "#value_13"
  val rightToBuy: String                     = "#value_14"
  val registeredSocial: String               = "#value_15"
  val alternativePropertyFinance: String     = "#value_16"
  val collectiveEnfranchisement: String      = "#value_17"
  val croftingRightToBuy: String             = "#value_18"
  val diplomaticPrivileges: String           = "#value_19"
  val otherRelief: String                    = "#value_20"
  val combinationOfReliefs: String           = "#value_21"
  val alternativeFinanceInvestment: String   = "#value_22"
  val firstTimeBuyer: String                 = "#value_23"
  val multipleDwellings: String              = "#value_24"
  val preCompletion: String                  = "#value_25"
  val reliefFromRate: String                 = "#value_26"
  val reliefForFreeport: String              = "#value_27"
  val reliefInvestmentZone: String           = "#value_28"
  val seedingRelief: String                  = "#value_29"

}
