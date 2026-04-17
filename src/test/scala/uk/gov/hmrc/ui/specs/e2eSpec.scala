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

package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Preliminary.*
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.pages.VendorAgent.*
import uk.gov.hmrc.ui.pages.Purchaser.*
import uk.gov.hmrc.ui.pages.PurchaserAgent.*
import uk.gov.hmrc.ui.pages.Land.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class e2eSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend end to end") {

    Scenario("Complete the end to end flow of the Filing Journey", e2eJourney) {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the PreliminaryBeforeYouStart page is shown")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)

      When("the user starts the preliminary questions")
      PreliminaryBeforeYouStart.saveAndContinue()
      Then("the PreliminaryWhoIsMakingThePurchase page is shown")
      PreliminaryWhoIsMakingThePurchase.verifyPageTitle(PreliminaryWhoIsMakingThePurchase.pageTitle)

      When("the user selects Company as the type of purchaser")
      PreliminaryWhoIsMakingThePurchase.radioButton(PreliminaryWhoIsMakingThePurchase.company)
      PreliminaryWhoIsMakingThePurchase.saveAndContinue()
      Then("the PreliminaryPurchaserName page is shown")
      PreliminaryPurchaserName.verifyPageTitle(PreliminaryPurchaserName.pageTitleCompany)

      When("the user provides the company name")
      PreliminaryPurchaserName.input(
        By.id(PreliminaryPurchaserName.companyName),
        PreliminaryPurchaserName.companyNameInput
      )
      PreliminaryPurchaserName.clickSubmitButton()
      Then("the PreliminaryPropertyAddress page is shown")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.pageTitle)

      When("the user enters the property address manually")
      PreliminaryPropertyAddress.clickAddressManually()
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.editPageTitle)
      PreliminaryPropertyAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the ConfirmPropertyAddress page is shown")
      PreliminaryPropertyAddress.verifyPageTitle(PreliminaryPropertyAddress.confirmPageTitle)

      When("the user confirms the property address")
      PreliminaryPropertyAddress.clickContinueButton()
      Then("the TransactionType page is shown")
      TransactionType.verifyPageTitle(TransactionType.pageTitle)

      When("the user selects Conveyance/transfer with lease involvement as the transaction type")
      TransactionType.radioButton(TransactionType.conveyance)
      TransactionType.saveAndContinue()
      Then("the PreliminaryCheckYourAnswers page is shown")
      PreliminaryCheckYourAnswers.verifyPageTitle(PreliminaryCheckYourAnswers.pageTitle)

      When("the user submits the preliminary questions")
      PreliminaryCheckYourAnswers.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

      When("the user opens the vendor questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-questions")
      Then("the VendorOverview page is shown")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)

      When("the user adds a new vendor")
      VendorOverview.radioButton(VendorOverview.yes)
      VendorOverview.saveAndContinue()
      Then("the VendorBeforeYouStart page is shown")
      VendorBeforeYouStart.verifyPageTitle(VendorBeforeYouStart.pageTitle)

      When("the user starts the vendor questions")
      VendorBeforeYouStart.saveAndContinue()
      Then("the WhoIsTheVendor page is shown")
      WhoIsTheVendor.verifyPageTitle(WhoIsTheVendor.pageTitle)

      When("the user selects Company as the vendor type")
      WhoIsTheVendor.radioButton(WhoIsTheVendor.company)
      WhoIsTheVendor.saveAndContinue()
      Then("the VendorName page is shown")
      WhoIsTheVendor.verifyPageTitle(VendorName.pageTitleCompany)

      When("the user provides the company name")
      VendorName.input(
        By.id(VendorName.companyName),
        VendorName.companyNameInput
      )
      VendorName.saveAndContinue()
      Then("the ConfirmVendorsAddress page is shown")
      ConfirmVendorsAddress.verifyPageTitle(ConfirmVendorsAddress.pageTitle)

      When("the user confirms to enter the vendor address manually")
      ConfirmVendorsAddress.radioButton(ConfirmVendorsAddress.no)
      ConfirmVendorsAddress.saveAndContinue()
      Then("the VendorPropertyAddress page is shown")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.pageTitle)

      When("the user enters the vendor address manually")
      VendorPropertyAddress.clickAddressManually()
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.editPageTitleBusiness)
      VendorPropertyAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the ConfirmVendorPropertyAddress page is shown")
      VendorPropertyAddress.verifyPageTitle(VendorPropertyAddress.confirmPageTitleBusiness)

      When("the user confirms the vendor address")
      VendorPropertyAddress.clickContinueButton()
      Then("the VendorCheckYourAnswers page is shown")
      VendorCheckYourAnswers.verifyPageTitle(VendorCheckYourAnswers.pageTitle)

      When("the user submits the vendor questions")
      VendorCheckYourAnswers.saveAndContinue()
      Then("the VendorOverview page is shown")
      VendorOverview.verifyPageTitle(VendorOverview.pageTitle)

      When("the user does not add another vendor")
      VendorOverview.radioButton(VendorOverview.no)
      VendorOverview.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

      When("the user opens the vendor agent questions")
      ReturnTaskList.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the VendorAgentBeforeYouStart page is shown")
      VendorAgentBeforeYouStart.verifyPageTitle(VendorAgentBeforeYouStart.pageTitle)

      When("the user starts the vendor agent questions")
      VendorAgentBeforeYouStart.radioButton(VendorAgentBeforeYouStart.yes)
      VendorAgentBeforeYouStart.saveAndContinue()
      Then("the VendorAgentName page is shown")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)

      When("the user provides the vendor agent name")
      VendorAgentName.input(
        By.id(VendorAgentName.agentName),
        VendorAgentName.agentNameInput
      )
      VendorAgentName.saveAndContinue()
      Then("the VendorAgentAddress page is shown")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle)

      When("the user enters the vendor agent address manually")
      VendorAgentAddress.clickAddressManually()
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent)
      VendorAgentAddress.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the Confirm Vendor Agent Property Address page is shown")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent)

      When("the user confirms the vendor agent address")
      VendorPropertyAddress.clickContinueButton()
      Then("the AddVendorAgentContactDetails page is shown")
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)

      When("the user confirms to not add vendor agent contact details")
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.no)
      AddVendorAgentContactDetails.saveAndContinue()
      Then("the AddVendorAgentReferenceNumber page is shown")
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)

      When("the user confirms to not add a vendor agent reference number")
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.no)
      AddVendorAgentReferenceNumber.saveAndContinue()
      Then("the VendorAgentCheckYourAnswers page is shown")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user submits the vendor agent questions")
      VendorAgentCheckYourAnswers.saveAndContinue()
      Then("the VendorAgentOverview page is shown")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)

      When("the user does not add another vendor agent")
      VendorAgentOverview.radioButton(VendorAgentOverview.no)
      VendorAgentOverview.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

      When("the user opens the purchaser questions")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-questions")
      Then("the Purchaser Overview page is shown")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)

      When("the user adds a new purchaser")
      PurchaserOverview.radioButton(PurchaserOverview.yes)
      PurchaserOverview.saveAndContinue()
      Then("the PurchaserBeforeYouStart page is shown")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)

      When("the user starts the purchaser questions")
      PurchaserBeforeYouStart.saveAndContinue()
      Then("the PurchaserWhoIsMakingThePurchase page is shown")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)

      When("the user chooses an individual as the purchaser")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.individual)
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the PurchaserName page is shown")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)

      When("the user inputs the purchaser first name, middle name, and surname")
      PurchaserName.input(
        By.id(PurchaserName.forenameId),
        PurchaserName.forenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.middlenameId),
        PurchaserName.middlenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.surnameId),
        PurchaserName.surnameInput
      )
      PurchaserName.saveAndContinue()
      Then("the PurchaserConfirmAddress page is shown")
      PurchaserConfirmAddress.verifyPageTitle(PurchaserConfirmAddress.pageTitle)

      When("the user confirms the purchasers address")
      PurchaserConfirmAddress.radioButton(PurchaserConfirmAddress.yes)
      PurchaserConfirmAddress.saveAndContinue()
      Then("the ActingAsATrustee page is shown")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)

      When("the user confirms the purchaser is acting as a trustee")
      ActingAsATrustee.radioButton(ActingAsATrustee.yes)
      ActingAsATrustee.saveAndContinue()
      Then("the PurchaserAndVendorConnected page is shown")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)

      When("the user confirms the purchaser and vendor are connected")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.yes)
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the PurchaserCheckYourAnswers page is shown")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)

      When("the user submits the purchaser questions")
      PurchaserCheckYourAnswers.saveAndContinue()
      Then("the PurchaserOverview page is shown")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)

      When("the user does not add another purchaser")
      PurchaserOverview.radioButton(PurchaserOverview.no)
      PurchaserOverview.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

      When("the user opens the purchaser agent questions")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the PurchaserAgentBeforeYouStart page is shown")
      PurchaserAgentBeforeYouStart.verifyPageTitle(PurchaserAgentBeforeYouStart.pageTitle)

      When("the user starts the purchaser agent questions")
      PurchaserAgentBeforeYouStart.radioButton(PurchaserAgentBeforeYouStart.yes)
      PurchaserAgentBeforeYouStart.saveAndContinue()
      Then("the SelectPurchaserAgent page is shown")
      SelectPurchaserAgent.verifyPageTitle(SelectPurchaserAgent.pageTitle)

      When("the user adds an existing agent")
      SelectPurchaserAgent.radioButton(SelectPurchaserAgent.selectAgent)
      SelectPurchaserAgent.saveAndContinue()
      Then("the AddPurchaserAgentReferenceNumber page is shown")
      AddPurchaserAgentReferenceNumber.verifyPageTitle(AddPurchaserAgentReferenceNumber.pageTitle)

      When("the user confirms to not add the purchaser agent reference")
      AddPurchaserAgentReferenceNumber.radioButton(AddPurchaserAgentReferenceNumber.no)
      AddPurchaserAgentReferenceNumber.saveAndContinue()
      Then("the PurchaserAgentAuthorisedForCorrespondence page is shown")
      PurchaserAgentAuthorisedForCorrespondence.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondence.pageTitle
      )

      When("the user confirms the purchaser agent is authorised for correspondence")
      PurchaserAgentAuthorisedForCorrespondence.radioButton(PurchaserAgentAuthorisedForCorrespondence.yes)
      PurchaserAgentAuthorisedForCorrespondence.saveAndContinue()
      Then("the PurchaserAgentCheckYourAnswers page is shown")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)

      When("the user submits the purchaser agent questions")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the PurchaserAgentOverview page is shown")
      PurchaserAgentOverview.verifyPageTitle(PurchaserAgentOverview.pageTitle)

      When("the user does not add another purchaser agent")
      PurchaserAgentOverview.radioButton(PurchaserAgentOverview.no)
      PurchaserAgentOverview.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

      When("the user starts the land questions")
      ReturnTaskList.clickLinkById("task-list-link-land-questions")
      Then("the LandOverview page is shown")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)

      When("the user adds a new area of land")
      LandOverview.radioButton(LandOverview.yes)
      LandOverview.saveAndContinue()
      Then("the LandBeforeYouStart page is shown")
      LandBeforeYouStart.verifyPageTitle(LandBeforeYouStart.pageTitle)

      When("the user starts the land questions")
      LandBeforeYouStart.saveAndContinue()
      Then("the TypeOfProperty page is shown")
      TypeOfProperty.verifyPageTitle(TypeOfProperty.pageTitle)

      When("the user selects residential as the property type")
      TypeOfProperty.radioButton(TypeOfProperty.residential)
      TypeOfProperty.saveAndContinue()
      Then("the InterestTransferredCreated page is shown")
      InterestTransferredCreated.verifyPageTitle(InterestTransferredCreated.pageTitle)

      When("the user selects long leasehold to describe the transaction")
      InterestTransferredCreated.radioButton(InterestTransferredCreated.LG)
      InterestTransferredCreated.saveAndContinue()
      Then("the LandAddress page is shown")
      LandAddress.verifyPageTitle(LandAddress.pageTitle)

      When("the user enters the land address manually")
      LandAddress.clickAddressManually()
      LandAddress.verifyPageTitle(LandAddress.editPageTitle)
      LandAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the ConfirmLandAddress page is shown")
      LandAddress.verifyPageTitle(LandAddress.confirmPageTitle)

      When("the user confirms the land address")
      LandAddress.clickContinueButton()
      Then("the LocalAuthorityCode page is shown")
      LocalAuthorityCode.verifyPageTitle(LocalAuthorityCode.pageTitle)

      When("the user provides a local authority code")
      LocalAuthorityCode.input(
        By.id(LocalAuthorityCode.localAuthCode),
        LocalAuthorityCode.localAuthCodeInput
      )
      LocalAuthorityCode.saveAndContinue()
      Then("the HMLandRegistration page is shown")
      HM_LandRegistration.verifyPageTitle(HM_LandRegistration.pageTitle)

      When("the user confirms the land is not registered with hm land registry")
      HM_LandRegistration.radioButton(HM_LandRegistration.no)
      HM_LandRegistration.saveAndContinue()
      Then("the AddNLPGUPRN page is shown")
      DoYouHaveNLPG.verifyPageTitle(DoYouHaveNLPG.pageTitle)

      When("the user confirms they do not have a NLPG UPRN")
      DoYouHaveNLPG.radioButton(DoYouHaveNLPG.no)
      DoYouHaveNLPG.saveAndContinue()
      Then("the SendingPlanByPost page is shown")
      LandSendingPlanByPost.verifyPageTitle(LandSendingPlanByPost.pageTitle)

      When("the user confirms they will send a plan by post")
      LandSendingPlanByPost.radioButton(LandSendingPlanByPost.yes)
      LandSendingPlanByPost.saveAndContinue()
      Then("the MineralsOrMineralRights page is shown")
      LandMineralsOrMineralRights.verifyPageTitle(LandMineralsOrMineralRights.pageTitle)

      When("the user confirms there are mineral or mineral rights reserved")
      LandMineralsOrMineralRights.radioButton(LandMineralsOrMineralRights.yes)
      LandMineralsOrMineralRights.saveAndContinue()
      Then("the LandCheckYourAnswers page is shown")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user submits the land questions")
      LandCheckYourAnswers.saveAndContinue()
      Then("the LandOverview page is shown")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)

      When("the user does not add another land")
      LandOverview.radioButton(LandOverview.no)
      LandOverview.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)

//      When("the user opens the uk residency questions")
//      ReturnTaskList.clickLinkById("task-list-link-uk-residency-questions")
//      Then("the UKResidencyBeforeYouStart page is shown")
//      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)
//
//      When("the user starts the uk residency questions")
//      UKResidencyBeforeYouStart.saveAndContinue()
//      Then("the ResidencyStatus page is shown")
//      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)
//      ResidencyStatus.clickResidencyStatusLink()
//
//      When("the user confirms there are non-UK resident purchasers")
//      ResidencyStatus.radioButton(ResidencyStatus.yes)
//      ResidencyStatus.saveAndContinue()
//      Then("the CrownEmploymentRelief page is shown")
//      CrownEmploymentRelief.verifyPageTitle(CrownEmploymentRelief.pageTitle)
//
//      When("the user confirms there are purchasers claiming Crown Employment relief")
//      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.yes)
//      CrownEmploymentRelief.saveAndContinue()
//      Then("the UKResidencyCheckYourAnswers page is shown")
//      UKResidencyCheckYourAnswers.verifyPageTitle(UKResidencyCheckYourAnswers.pageTitle)
//
//      When("the user submits the uk residency questions")
//      UKResidencyCheckYourAnswers.saveAndContinue()
//      Then("the ReturnTaskList page is shown")
//      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)
    }
  }
}
