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
import uk.gov.hmrc.ui.pages.Land.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.selenium.webdriver.Driver.instance

class LandQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend About The Land Questions") {

    Scenario(
      "Complete the Land Questions user journey with Residential property type)",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))

      When("the user clicks on the 'Land Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Land Before you start page page and clicks continue")
      LandBeforeYouStart.verifyPageTitle(LandBeforeYouStart.pageTitle)
      LandBeforeYouStart.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfProperty.verifyPageTitle(TypeOfProperty.pageTitle)

      When("the user selects the 'Residental Property' and continues")
      TypeOfProperty.radioButton(TypeOfProperty.residential)
      TypeOfProperty.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreated.verifyPageTitle(InterestTransferredCreated.pageTitle)

      When("the user selects the 'Long Leasehold' radio button and continues")
      InterestTransferredCreated.radioButton(InterestTransferredCreated.LG)
      InterestTransferredCreated.saveAndContinue()
      Then("the user is navigated to the Land Confirm Address page")
      LandConfirmAddress.verifyPageTitle(LandConfirmAddress.pageTitle)

      When("the user selects the 'No' radio button and continues")
      LandConfirmAddress.radioButton(LandConfirmAddress.no)
      LandConfirmAddress.saveAndContinue()
      Then("the user is navigated to the Land Address Lookup page")
      LandAddress.verifyPageTitle(LandAddress.pageTitle)

      When("the user choose to 'Enter the address manually' link")
      LandAddress.clickAddressManually()
      And("enters their address manually")
      LandAddress.verifyPageTitle(LandAddress.editPageTitle)
      LandAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      And("the user review and confirm the address and click confirm address")
      LandAddress.verifyPageTitle(LandAddress.confirmPageTitle)
      LandAddress.clickContinueButton()
      Then("the user is on What is the local authority code")
      LocalAuthorityCode.verifyPageTitle(LocalAuthorityCode.pageTitle)
      And("the user clicks on the link list of local auth codes")
      LocalAuthorityCode.validateListOfLocalAuthCodeLink()

      When("the user enters a valid local authority code and continues")
      LocalAuthorityCode.input(
        By.id(LocalAuthorityCode.localAuthCode),
        LocalAuthorityCode.localAuthCodeInput
      )
      LocalAuthorityCode.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistration.verifyPageTitle(HM_LandRegistration.pageTitle)

      When("the user selects No and continues")
      HM_LandRegistration.radioButton(HM_LandRegistration.no)
      HM_LandRegistration.saveAndContinue()
      Then("the user is on Do you have an NLPG UPRN for the land or property ?")
      DoYouHaveNLPG.verifyPageTitle(DoYouHaveNLPG.pageTitle)

      And("selects 'No' for having NLPG UPRN and continues")
      DoYouHaveNLPG.radioButton(DoYouHaveNLPG.no)
      DoYouHaveNLPG.saveAndContinue()
      Then("the user is navigated to the Sending Plan By Post page")
      LandSendingPlanByPost.verifyPageTitle(LandSendingPlanByPost.pageTitle)

      When("the user clicks the dropdown window")
      LandSendingPlanByPost.clickDropdownText()
      And("the dropdown text appears")
      LandSendingPlanByPost.verifyPageText(LandSendingPlanByPost.dropdownText, 1)

      When("the user selects the 'Yes' radio button and continues")
      LandSendingPlanByPost.radioButton(LandSendingPlanByPost.yes)
      LandSendingPlanByPost.saveAndContinue()
      Then("the user is navigated to the Land Minerals Or Mineral Rights page")
      LandMineralsOrMineralRights.verifyPageTitle(LandMineralsOrMineralRights.pageTitle)

      When("the user selects the 'No' radio button and continues")
      LandMineralsOrMineralRights.radioButton(LandMineralsOrMineralRights.no)
      LandMineralsOrMineralRights.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Interest transferred or created")
      LandCheckYourAnswers.clickInterestTransferredOrCreated()
      And("selects the 'Other' radio button and continues")
      InterestTransferredCreated.radioButton(InterestTransferredCreated.OT)
      InterestTransferredCreated.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for address")
      LandCheckYourAnswers.clickLandAddress()
      When("the user clicks on the 'Enter the address manually' link")
      LandAddress.clickAddressManually()
      And("enters their address manually")
      LandAddress.verifyPageTitle(LandAddress.editPageTitle)
      LandAddress.enterAddressManually("1 Silver Lane", "Test Town", "ZZ11 1ZZ")
      And("the user review and confirm the address and click confirm address")
      LandAddress.verifyPageTitle(LandAddress.confirmPageTitle)
      LandAddress.clickContinueButton()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Local Authority Code")
      LandCheckYourAnswers.clickLocalAuthorityCode()
      And("enters a different valid local authority code and continues")
      LocalAuthorityCode.input(
        By.id(LocalAuthorityCode.localAuthCode),
        LocalAuthorityCode.welshLocalAuthCodeInput
      )
      LocalAuthorityCode.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Is the land or property registered  with HM Land Registry ?")
      LandCheckYourAnswers.clickHMLandRegistration()
      When("selects yes and continues")
      HM_LandRegistration.radioButton(HM_LandRegistration.yes)
      HM_LandRegistration.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks 'Select title or folio number' link")
      LandCheckYourAnswers.clickTitleNumber()
      And("The user enters Land Title Number and continues")
      LandTitleNumber.input(By.id(LandTitleNumber.landTitleNumber), LandTitleNumber.landTitleNumberInput)
      LandTitleNumber.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Do you have an NLPG UPRN?")
      LandCheckYourAnswers.clickDoYouHaveNLPGUPRN()
      And("selects yes and continues")
      DoYouHaveNLPG.radioButton(DoYouHaveNLPG.yes)
      DoYouHaveNLPG.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks 'Enter Land NLPG UPRN' link")
      LandCheckYourAnswers.clickEnterNLPGUPRN()
      And("enters NLPG UPRN and continues")
      NLPGUPRN.input(By.id(NLPGUPRN.nlpg_uprn), NLPGUPRN.nlpg_uprnInput)
      NLPGUPRN.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user selects Save and continue")
      LandCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Land Overview page")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)

      When("the user selects the 'no' radio button and continues")
      LandOverview.radioButton(LandOverview.no)
      LandOverview.saveAndContinue()
      Then("the user is navigated to return task list page")
      ReturnTaskListPage.verifyPageTitle(ReturnTaskListPage.pageTitle)
    }

    Scenario(
      "Complete the Land Questions user journey with Mixed property type navigating to agricultural land questions",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("1-land-residential-property-type"))

      When("the user clicks on the 'Land Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-land-questions")
      Then("the user is navigated to the Land Overview page")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)

      When(" the user selects yes from the overview page and continues")
      LandOverview.radioButton(LandOverview.yes)
      LandOverview.saveAndContinue()
      Then("the user should be navigated to the Land Before you start page page")
      LandBeforeYouStart.verifyPageTitle(LandBeforeYouStart.pageTitle)

      When("the user clicks the Continue button")
      LandBeforeYouStart.saveAndContinue()
      Then("the user should be navigated to What is the type of property page")
      TypeOfProperty.verifyPageTitle(TypeOfProperty.pageTitle)

      When("the user selects the 'Mixed' radio button and continues")
      TypeOfProperty.radioButton(TypeOfProperty.mixed)
      TypeOfProperty.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreated.verifyPageTitle(InterestTransferredCreated.pageTitle)

      When("the user selects the 'Leasehold subject' radio button and continues")
      InterestTransferredCreated.radioButton(InterestTransferredCreated.LT)
      InterestTransferredCreated.saveAndContinue()
      Then("the user is navigated to the Land Address Look up page")
      LandAddress.verifyPageTitle(LandAddress.pageTitle)

      When("the user choose to 'Enter the address manually' link")
      LandAddress.clickAddressManually()
      And("enters their address manually")
      LandAddress.verifyPageTitle(LandAddress.editPageTitle)
      LandAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      And("the user review and confirm the address and click confirm address")
      LandAddress.verifyPageTitle(LandAddress.confirmPageTitle)
      LandAddress.clickContinueButton()
      Then("the user is on What is the local authority code")
      LocalAuthorityCode.verifyPageTitle(LocalAuthorityCode.pageTitle)

      When("the user enters a valid local authority code and continues")
      LocalAuthorityCode.input(
        By.id(LocalAuthorityCode.localAuthCode),
        LocalAuthorityCode.localAuthCodeInput
      )
      LocalAuthorityCode.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistration.verifyPageTitle(HM_LandRegistration.pageTitle)

      When("the user selects Yes for HM Land Registry and continues")
      HM_LandRegistration.radioButton(HM_LandRegistration.yes)
      HM_LandRegistration.saveAndContinue()
      Then("The user is navigated to the Land Title Number Page")
      LandTitleNumber.verifyPageTitle(LandTitleNumber.pageTitle)

      When("the user enters Land Title Number and continues")
      LandTitleNumber.input(By.id(LandTitleNumber.landTitleNumber), LandTitleNumber.landTitleNumberInput)
      LandTitleNumber.saveAndContinue()
      Then("user is on Do you have an NLPG UPRN for the land or property ? page")
      DoYouHaveNLPG.verifyPageTitle(DoYouHaveNLPG.pageTitle)

      When("the user selects Yes for having NLPG UPRN and continues")
      DoYouHaveNLPG.radioButton(DoYouHaveNLPG.yes)
      DoYouHaveNLPG.saveAndContinue()
      Then("the user should be navigated to What is the NLPG UPRN for the land or property page")
      NLPGUPRN.verifyPageTitle(NLPGUPRN.pageTitle)

      When("the user enters a valid NLPG UPRN and continues")
      NLPGUPRN.input(By.id(NLPGUPRN.nlpg_uprn), NLPGUPRN.nlpg_uprnInput)
      NLPGUPRN.saveAndContinue()
      Then("the user is navigated to the Sending Plan By Post page")
      LandSendingPlanByPost.verifyPageTitle(LandSendingPlanByPost.pageTitle)

      When("the user selects the 'No' radio button and continues")
      LandSendingPlanByPost.radioButton(LandSendingPlanByPost.no)
      LandSendingPlanByPost.saveAndContinue()
      Then("the user is navigated to the Land Minerals Or Mineral Rights page")
      LandMineralsOrMineralRights.verifyPageTitle(LandMineralsOrMineralRights.pageTitle)

      When("the user selects the 'Yes' radio button and continues")
      LandMineralsOrMineralRights.radioButton(LandMineralsOrMineralRights.yes)
      LandMineralsOrMineralRights.saveAndContinue()
      Then("the user is navigated to Agricultural Or Developmental Land Page")
      AgriculturalOrDevelopmentalLand.verifyPageTitle(AgriculturalOrDevelopmentalLand.pageTitle)

      When("the user selects yes on the page and continues")
      AgriculturalOrDevelopmentalLand.radioButton(AgriculturalOrDevelopmentalLand.yes)
      AgriculturalOrDevelopmentalLand.saveAndContinue()
      Then("the user is navigated to Do you know the area of the land Page")
      AddAreaOfTheLand.verifyPageTitle(AddAreaOfTheLand.pageTitle)

      When("the user select 'yes' on the page continues")
      AddAreaOfTheLand.radioButton(AddAreaOfTheLand.yes)
      AddAreaOfTheLand.saveAndContinue()
      Then("User is navigated to Select one unit of measurement for the area of land Page")
      UnitOfMeasurement.verifyPageTitle(UnitOfMeasurement.pageTitle)

      When("the user selects 'square metres' as unit of measurement and continues")
      UnitOfMeasurement.radioButton(UnitOfMeasurement.squareMetres)
      UnitOfMeasurement.saveAndContinue()
      Then("User is navigated to area of the land in square metres page")
      AreaOfTheLand.verifyPageTitle(AreaOfTheLand.pageTitle)
      AreaOfTheLand.verifyElementIsDisplayed(AreaOfTheLand.headerSquareMetre)
      And("The user go back and change the unit of measurement as 'hectares' and continues")
      AreaOfTheLand.clickBackLink()
      UnitOfMeasurement.radioButton(UnitOfMeasurement.hectares)
      UnitOfMeasurement.saveAndContinue()
      Then("User is navigated to area of the land in hectares page")
      AreaOfTheLand.verifyPageTitle(AreaOfTheLand.pageTitle)
      AreaOfTheLand.verifyElementIsDisplayed(AreaOfTheLand.headerHectares)

      When(" user enters area in hectares and continues")
      AreaOfTheLand.input(By.id(AreaOfTheLand.area), AreaOfTheLand.areaHectares)
      AreaOfTheLand.saveAndContinue()
      Then("the user navigated to CYA page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Type of property?")
      LandCheckYourAnswers.clickTypeOfPropertyChange()
      And("change the property type as 'Non-Residential' and continues")
      TypeOfProperty.radioButton(TypeOfProperty.non_residential)
      TypeOfProperty.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Do you have an NLPG UPRN for the land or property?")
      LandCheckYourAnswers.clickDoYouHaveNLPGUPRN()
      And("selects 'no' for having NLPG UPRN and continues")
      DoYouHaveNLPG.radioButton(DoYouHaveNLPG.no)
      DoYouHaveNLPG.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for sending plan by post")
      LandCheckYourAnswers.clickSendingPlanByPost()
      And("selects the 'yes' radio button and continues")
      LandSendingPlanByPost.radioButton(LandSendingPlanByPost.yes)
      LandSendingPlanByPost.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Minerals or Mineral rights")
      LandCheckYourAnswers.clickMineralsOrMineralRights()
      And("selects the 'No' radio button and continues")
      LandMineralsOrMineralRights.radioButton(LandMineralsOrMineralRights.no)
      LandMineralsOrMineralRights.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user clicks the 'Change' link for Agricultural or developmental land")
      LandCheckYourAnswers.clickAgriculturalOrDevelopmentalLand()
      And("selects the 'No' radio button and continues and continues")
      AgriculturalOrDevelopmentalLand.radioButton(AgriculturalOrDevelopmentalLand.no)
      AgriculturalOrDevelopmentalLand.saveAndContinue()
      Then("the user is on Check your answers page")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)

      When("the user selects Save and continue")
      LandCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Land Overview page")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)
    }

    Scenario(
      "Validating the overview, update and removing functionality of a Land",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("5-lands")
      )

      When("the user clicks on the 'Land Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-land-questions")
      Then("the user is navigated to the Land Overview page")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)

      When("the user clicks the 'Remove' link to remove land")
      LandOverview.clickLandRemove()
      Then("the user is navigated to remove land page")
      LandRemove.verifyPageTitle(LandRemove.pageTitle)

      When("the user selects no radio button and continues")
      LandRemove.radioButton(LandRemove.no)
      LandRemove.saveAndContinue()
      Then("the user is navigated to the Land Overview page")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)

      When("the user clicks the 'Remove' link to remove land")
      LandOverview.clickLandRemove()
      And("selects yes radio button and continues")
      LandRemove.radioButton(LandRemove.yes)
      LandRemove.saveAndContinue()
      Then("the user is navigated to the Land Overview page with a success banner")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)
      LandOverview.verifyElementIsDisplayed(LandOverview.removeBanner)

      When("the user clicks the 'change' link to update land details")
      LandOverview.clickLandChange()
      And("user navigates to Check Your Answers page and continues")
      LandCheckYourAnswers.verifyPageTitle(LandCheckYourAnswers.pageTitle)
      LandCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Land Overview page with a success banner")
      LandOverview.verifyPageTitle(LandOverview.pageTitle)
      LandOverview.verifyElementIsDisplayed(LandOverview.updateBanner)
    }
  }
}
