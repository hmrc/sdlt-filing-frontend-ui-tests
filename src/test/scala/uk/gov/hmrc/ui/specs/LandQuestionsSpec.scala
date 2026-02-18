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
      "Complete the Land Questions user journey Address confirmed, registered with Land Registry, UPRN available (Title and UPRN captured)",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Land Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      When("the user selects the 'Residential' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.residential)
      And("clicks the Save and continue button")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Freehold' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.FG)
      Then(" the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      Then("the user is navigated to the Land Confirm Address page")
      LandConfirmAddressPage.verifyPageTitle(LandConfirmAddressPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      LandConfirmAddressPage.radioButton(LandConfirmAddressPage.yes)
      And("clicks the Confirm Address button")
      LandConfirmAddressPage.saveAndContinue()
      Then("the user is on What is the local authority code")
      LocalAuthorityCodePage.verifyPageTitle(LocalAuthorityCodePage.pageTitle)
      And("the user enters a valid local authority code")
      LocalAuthorityCodePage.input(
        By.id(LocalAuthorityCodePage.localAuthCode),
        LocalAuthorityCodePage.localAuthCodeInput
      )
      And("the user clicks on Save & Continue")
      LocalAuthorityCodePage.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistrationPage.verifyPageTitle(HM_LandRegistrationPage.pageTitle)
      When("the user selects Yes and continues")
      HM_LandRegistrationPage.radioButton(HM_LandRegistrationPage.yes)
      And("clicks the Save and continue button")
      HM_LandRegistrationPage.saveAndContinue()
      Then("The user is navigated to the Land Title Number Page")
      LandTitleNumberPage.verifyPageTitle(LandTitleNumberPage.pageTitle)
      When("The user enters Land Title Number")
      LandTitleNumberPage.input(By.id(LandTitleNumberPage.landTitleNumber), LandTitleNumberPage.landTitleNumberInput)
      And("clicks the Save and continue button")
      LandTitleNumberPage.saveAndContinue()
      Then("the user is on Do you have an NLPG UPRN for the land or property ?")
      DoYouHaveNLPGPage.verifyPageTitle(DoYouHaveNLPGPage.pageTitle)
      And("selects 'Yes' for having NLPG UPRN")
      DoYouHaveNLPGPage.radioButton(DoYouHaveNLPGPage.yes)
      And("clicks the Save and continue button")
      DoYouHaveNLPGPage.saveAndContinue()
      // Then the user is on What is the NLPG UPRN for the land or property*/
      // When the user enters a valid NLPG UPRN and continues
      // Then the user is on Will you be sending a plan by post ?
      // When the user  selects yes and continues
      // Then the user is on Minerals or mineral rights(Are there any mineral or mineral rights reserved or excluded for the land or property ?)
      // When the user selects "No" and continues
      // Then the user is on Check your answers
      // When the user selects Save and continue
      // Then the user is on the Overview page
    }

    Scenario(
      "Complete the Land Questions user journey address lookup used, unregistered with Land Registry, no UPRN (Title and UPRN skipped)",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Land Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Additional Residental Property' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.additional_residential)
      And("clicks the Save and continue button")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Long Leasehold' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.LG)
      And("clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      Then("the user is navigated to the Land Confirm Address page")
      LandConfirmAddressPage.verifyPageTitle(LandConfirmAddressPage.pageTitle)
      When("the user selects the 'No' radio button")
      LandConfirmAddressPage.radioButton(LandConfirmAddressPage.no)
      And("clicks the Confirm Address button")
      LandConfirmAddressPage.saveAndContinue()
      Then("the user is navigated to the Land Address page")
      LandAddressPage.verifyPageTitle(LandAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      LandAddressPage.clickAddressManually()
      And("enters their address manually")
      LandAddressPage.verifyPageTitle(LandAddressPage.editPageTitle)
      LandAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Land Address page to 'Review and confirm the address'")
      LandAddressPage.verifyPageTitle(LandAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      LandAddressPage.clickContinueButton()
      Then("the user is on What is the local authority code")
      LocalAuthorityCodePage.verifyPageTitle(LocalAuthorityCodePage.pageTitle)
      And("the user enters a valid local authority code")
      LocalAuthorityCodePage.input(
        By.id(LocalAuthorityCodePage.localAuthCode),
        LocalAuthorityCodePage.localAuthCodeInput
      )
      And("the user clicks on Save & Continue")
      LocalAuthorityCodePage.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistrationPage.verifyPageTitle(HM_LandRegistrationPage.pageTitle)
      When("the user selects No and continues")
      HM_LandRegistrationPage.radioButton(HM_LandRegistrationPage.no)
      And("clicks the Save and continue button")
      HM_LandRegistrationPage.saveAndContinue()
      Then("the user is on Do you have an NLPG UPRN for the land or property ?")
      DoYouHaveNLPGPage.verifyPageTitle(DoYouHaveNLPGPage.pageTitle)
      And("selects 'No' for having NLPG UPRN")
      DoYouHaveNLPGPage.radioButton(DoYouHaveNLPGPage.no)
      And("clicks the Save and continue button")
      DoYouHaveNLPGPage.saveAndContinue()
      // Then the user is on Will you be sending a plan by post ?
      // When the user selects Yes/No radio button and continues
      // Then the user is on Minerals or mineral rights(Are there any mineral or mineral rights reserved or excluded for the land or property ?)
      // When the user selects "No" and continues
      // Then the user is on Check your answers
      // When the user selects Save and continue
      // Then the user is on the Overview page
    }

    Scenario(
      "Complete the Land Questions user journey with Mixed property with mineral rights routes to agricultural land question and exits without land details",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Land Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Mixed' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.mixed)
      And("clicks the Save and continue button")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Leasehold subject' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.LT)
      And("clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      Then("the user is navigated to the Land Confirm Address page")
      LandConfirmAddressPage.verifyPageTitle(LandConfirmAddressPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      LandConfirmAddressPage.radioButton(LandConfirmAddressPage.yes)
      And("clicks the Confirm Address button")
      LandConfirmAddressPage.saveAndContinue()
      Then("the user is on What is the local authority code")
      LocalAuthorityCodePage.verifyPageTitle(LocalAuthorityCodePage.pageTitle)
      And("the user enters a valid local authority code")
      LocalAuthorityCodePage.input(
        By.id(LocalAuthorityCodePage.localAuthCode),
        LocalAuthorityCodePage.localAuthCodeInput
      )
      And("the user clicks on Save & Continue")
      LocalAuthorityCodePage.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistrationPage.verifyPageTitle(HM_LandRegistrationPage.pageTitle)
      When("the user selects Yes and continues")
      HM_LandRegistrationPage.radioButton(HM_LandRegistrationPage.yes)
      And("clicks the Save and continue button")
      HM_LandRegistrationPage.saveAndContinue()
      Then("The user is navigated to the Land Title Number Page")
      LandTitleNumberPage.verifyPageTitle(LandTitleNumberPage.pageTitle)
      When("The user enters Land Title Number")
      LandTitleNumberPage.input(By.id(LandTitleNumberPage.landTitleNumber), LandTitleNumberPage.landTitleNumberInput)
      And("clicks the Save and continue button")
      LandTitleNumberPage.saveAndContinue()
      Then("user is on Do you have an NLPG UPRN for the land or property ? page")
      DoYouHaveNLPGPage.verifyPageTitle(DoYouHaveNLPGPage.pageTitle)
      And("selects 'No' for having NLPG UPRN")
      DoYouHaveNLPGPage.radioButton(DoYouHaveNLPGPage.no)
      And("user clicks Save & Continue")
      DoYouHaveNLPGPage.saveAndContinue()
      // Then the user is on Will you be sending a plan by post ?
      // When user selects yes/No radio button and continues
      // Then the user is on Minerals or mineral rights(Are there any mineral or mineral rights reserved or excluded for the land or property ?)
      // When the user selects "Yes" and continues
      // Then the user is on Agricultural or development land (Does the transaction involve agricultural or development land ?)
      // When the user selects "No" and continues
      // Then the user is on Check your answers
      // When the user selects Save and continue
      // Then the user is on the Overview page
    }

    Scenario(
      "Complete the Land Questions user journey for  Non residential property with mineral rights and agricultural land without land area details",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Land Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Non-Residential' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.non_residential)
      And("clicks the Save and continue button")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Other' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.OT)
      Then("the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      Then("the user is navigated to the Land Confirm Address page")
      LandConfirmAddressPage.verifyPageTitle(LandConfirmAddressPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      LandConfirmAddressPage.radioButton(LandConfirmAddressPage.yes)
      And("clicks the Confirm Address button")
      LandConfirmAddressPage.saveAndContinue()
      Then("the user is on What is the local authority code")
      LocalAuthorityCodePage.verifyPageTitle(LocalAuthorityCodePage.pageTitle)
      And("the user enters a valid local authority code")
      LocalAuthorityCodePage.input(
        By.id(LocalAuthorityCodePage.localAuthCode),
        LocalAuthorityCodePage.localAuthCodeInput
      )
      And("the user clicks on Save & Continue")
      LocalAuthorityCodePage.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistrationPage.verifyPageTitle(HM_LandRegistrationPage.pageTitle)
      When("the user selects Yes and continues")
      HM_LandRegistrationPage.radioButton(HM_LandRegistrationPage.yes)
      And("clicks the Save and continue button")
      HM_LandRegistrationPage.saveAndContinue()
      Then("The user is navigated to the Land Title Number Page")
      LandTitleNumberPage.verifyPageTitle(LandTitleNumberPage.pageTitle)
      When("The user enters Land Title Number")
      LandTitleNumberPage.input(By.id(LandTitleNumberPage.landTitleNumber), LandTitleNumberPage.landTitleNumberInput)
      And("The user selects save and continue")
      LandTitleNumberPage.saveAndContinue()
      When("user is on Do you have an NLPG UPRN for the land or property ? page")
      DoYouHaveNLPGPage.verifyPageTitle(DoYouHaveNLPGPage.pageTitle)
      And("selects 'Yes' for having NLPG UPRN")
      DoYouHaveNLPGPage.radioButton(DoYouHaveNLPGPage.yes)
      And("clicks the Save and continue button")
      DoYouHaveNLPGPage.saveAndContinue()
      // And("user enters a valid NLPG UPRN and continues")
      // And("selects whether sending a plan by post")
      // And("selects 'Yes' for minerals or mineral rights")
      // And("selects 'Yes' for agricultural or development land")
      // And("selects 'No' for knowing the area of land")
      // Then("the user navigated to CYA page")
    }

    Scenario(
      "Complete the Land Questions user journey for  Non residential property with minerals, agricultural area in square meters",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Land Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Non-Residential' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.non_residential)
      And("clicks the Save and continue button")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Freehold vacant position' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.FP)
      And("clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      Then("the user is navigated to the Land Confirm Address page")
      LandConfirmAddressPage.verifyPageTitle(LandConfirmAddressPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      LandConfirmAddressPage.radioButton(LandConfirmAddressPage.yes)
      And("clicks the Confirm Address button")
      LandConfirmAddressPage.saveAndContinue()
      Then("the user is on What is the local authority code")
      LocalAuthorityCodePage.verifyPageTitle(LocalAuthorityCodePage.pageTitle)
      And("the user enters a valid local authority code")
      LocalAuthorityCodePage.input(
        By.id(LocalAuthorityCodePage.localAuthCode),
        LocalAuthorityCodePage.localAuthCodeInput
      )
      And("the user clicks on Save & Continue")
      LocalAuthorityCodePage.saveAndContinue()
      Then("the user is on Is the land or property registered  with HM Land Registry ?")
      HM_LandRegistrationPage.verifyPageTitle(HM_LandRegistrationPage.pageTitle)
      When("the user selects Yes and continues")
      HM_LandRegistrationPage.radioButton(HM_LandRegistrationPage.yes)
      And("clicks the Save and continue button")
      HM_LandRegistrationPage.saveAndContinue()
      Then("The user is navigated to the Land Title Number Page")
      LandTitleNumberPage.verifyPageTitle(LandTitleNumberPage.pageTitle)
      When("The user enters Land Title Number")
      LandTitleNumberPage.input(By.id(LandTitleNumberPage.landTitleNumber), LandTitleNumberPage.landTitleNumberInput)
      And("The user selects save and continue")
      LandTitleNumberPage.saveAndContinue()
      Then("the user is on Do you have an NLPG UPRN for the land or property ?")
      DoYouHaveNLPGPage.verifyPageTitle(DoYouHaveNLPGPage.pageTitle)
      And("selects 'No' for having NLPG UPRN")
      DoYouHaveNLPGPage.radioButton(DoYouHaveNLPGPage.no)
      And("clicks the Save and continue button")
      DoYouHaveNLPGPage.saveAndContinue()
      // And("selects whether sending a plan by post")
      // And("selects 'Yes' for minerals or mineral rights")
      // And("selects 'Yes' for agricultural or development land")
      // And("selects 'Yes' for knowing the area of land")
      // And user navigates to Select one unit of measurement for the area of land
      // And("selects 'square metres' as unit of measurement")
      // And("enters area in square metres")
      // Then("the user navigated to CYA page")
      // And user clicks on change link for unit of measurement
      // And user changes unit of measurement to hectares
      // And user enters area in hectares
      // Then("the user navigated to CYA page")
    }
  }
}
