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
import uk.gov.hmrc.ui.pages.PurchaserAgent.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class PurchaserAgentQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Purchaser Agent Journey") {
    Scenario("Complete the Purchaser Agent Journey for new return", PurchaserAgentJourney) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("User navigated to Select Purchaser Agent Details page")
      PurchaserAgentDetailsPage.verifyPageTitle(PurchaserAgentDetailsPage.pageTitle)
      When("User selects 'Add a new agent for this return' radio button")
      PurchaserAgentDetailsPage.radioButton(PurchaserAgentDetailsPage.addNewAgent)
      And("User clicks save and continue")
      PurchaserAgentDetailsPage.saveAndContinue()
      Then("The user should be navigated to the Agents Name Page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      And("The user enters their purchaser Agents name")
      PurchaserAgentName.input(By.id(PurchaserAgentName.agentName), PurchaserAgentName.agentNameInput)
      And("The User selects save and continue")
      PurchaserAgentName.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Address page")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.editPageTitleAgent)
      PurchaserAgentAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      PurchaserAgentAddressPage.clickContinueButton()
      Then("The user is navigated to the Does The Purchaser Agent have Contact Details page ")
      DoesPurchaserAgentHaveContactDetailsPage.verifyPageTitle(DoesPurchaserAgentHaveContactDetailsPage.pageTitle)
      And("The user clicks yes")
      DoesPurchaserAgentHaveContactDetailsPage.radioButton(DoesPurchaserAgentHaveContactDetailsPage.yes)
      And("The user selects save and continue")
      DoesPurchaserAgentHaveContactDetailsPage.saveAndContinue()
      Then("User should be navigated to Agent Contact Details Page")
      PurchaserAgentContactDetails.verifyPageTitle(PurchaserAgentContactDetails.pageTitle)
      When("User enters Purchaser Agent's phone number")
      PurchaserAgentContactDetails.input(
        By.id(PurchaserAgentContactDetails.emailAddress),
        PurchaserAgentContactDetails.emailAddressInput
      )
      And("User enters Purchaser Agent's email address")
      PurchaserAgentContactDetails.input(
        By.id(PurchaserAgentContactDetails.phoneNumber),
        PurchaserAgentContactDetails.phoneNumberInput
      )
      And("User clicks save and continue")
      PurchaserAgentContactDetails.saveAndContinue()
      Then("User navigated to Do you want to add reference for this return page")
      PurchaserAgentAddReferencePage.verifyPageTitle(PurchaserAgentAddReferencePage.pageTitle)
      And("User selects No radio button")
      PurchaserAgentAddReferencePage.radioButton(PurchaserAgentAddReferencePage.no)
      And("User clicks save and continue")
      PurchaserAgentAddReferencePage.saveAndContinue()
      Then("User Is navigated to Is Purchaser Agent authorised to have Correspondence Page")
      PurchaserAgentCorrespondencePage.verifyPageTitle(PurchaserAgentCorrespondencePage.pageTitle)
      And("The user selects the Yes Radio Button")
      PurchaserAgentCorrespondencePage.radioButton(PurchaserAgentCorrespondencePage.yes)
      And("The user clicks save and continue")
      PurchaserAgentCorrespondencePage.saveAndContinue()
      Then("User should be navigated to purchaser Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for Purchaser Agent's name")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentNameChange()
      Then("User should be navigated to Purchaser Agent's name page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      And("User updates agent name details")
      PurchaserAgentName.input(By.id(PurchaserAgentName.agentName), PurchaserAgentName.agentNameInput2)
      Then("User clicks on save and continue")
      PurchaserAgentName.saveAndContinue()
      And("User should be navigated to purchaser Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      Then("User clicks on change link for Purchaser Agent's address")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentAddressChange()
      And("User should be navigated to Purchaser Agent's address page")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.pageTitle2)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddressPage.clickAddressManually()
      And("enters their updated address manually")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.editPageTitleAgent2)
      PurchaserAgentAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.confirmPageTitleAgent2)
      And("User clicks on save and continue")
      PurchaserAgentAddressPage.clickContinueButton()
      Then("User should be navigated to purchasers Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for Do you want to add agent contact details")
      PurchaserAgentCheckYourAnswers.clickAddAgentContactDetailsChange()
      Then("User is navigated to Do you want to add agent contact details page")
      DoesPurchaserAgentHaveContactDetailsPage.verifyPageTitle(DoesPurchaserAgentHaveContactDetailsPage.pageTitle)
      And("The user selects no")
      DoesPurchaserAgentHaveContactDetailsPage.radioButton(DoesPurchaserAgentHaveContactDetailsPage.no)
      And("The user selects save and continue")
      DoesPurchaserAgentHaveContactDetailsPage.saveAndContinue()
      Then("User should be navigated to purchasers Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for Do you want to add Reference Number")
      PurchaserAgentCheckYourAnswers.clickDoYouWantToAddReferenceChange()
      Then("User navigated to Do you want to add reference for this return page")
      PurchaserAgentAddReferencePage.verifyPageTitle(PurchaserAgentAddReferencePage.pageTitle)
      And("User selects Yes radio button")
      PurchaserAgentAddReferencePage.radioButton(PurchaserAgentAddReferencePage.yes)
      And("User clicks save and continue")
      PurchaserAgentAddReferencePage.saveAndContinue()
      Then("User should be navigated to purchasers Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for enter agent reference number")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentReferenceNumberChange()
      Then("User is navigated to What is Agent's reference for this return page")
      PurchaserAgentReferencePage.verifyPageTitle(PurchaserAgentReferencePage.pageTitle)
      And("User enter Reference value")
      PurchaserAgentReferencePage.input(
        By.id(PurchaserAgentReferencePage.purchaserReference),
        PurchaserAgentReferencePage.purchaserReferenceInput
      )
      And("User clicks save and continue")
      PurchaserAgentReferencePage.saveAndContinue()
      Then("User should be navigated to purchasers Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for Is Agent authorized for Correspondence")
      PurchaserAgentCheckYourAnswers.clickIsAgentAuthorizedForCorrespondenceChange()
      Then("User Is navigated to Is Purchaser Agent authorised to have Correspondence Page")
      PurchaserAgentCorrespondencePage.verifyPageTitle(PurchaserAgentCorrespondencePage.pageTitle)
      And("The user selects the No Radio Button")
      PurchaserAgentCorrespondencePage.radioButton(PurchaserAgentCorrespondencePage.no)
      And("The user clicks save and continue")
      PurchaserAgentCorrespondencePage.saveAndContinue()
      Then("User should be navigated to purchasers Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("clicks the save and continue button")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user selects 'Yes' radio button")
      PurchaserAgentOverviewPage.radioButton(PurchaserAgentOverviewPage.yes)
      And("clicks the save and continue button")
      PurchaserAgentOverviewPage.saveAndContinue()
    }

    Scenario("Complete the Purchaser Agent Journey with no contact info", PurchaserAgentJourney) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("User navigated to Select Purchaser Agent Details page")
      PurchaserAgentDetailsPage.verifyPageTitle(PurchaserAgentDetailsPage.pageTitle)
      When("User selects 'Add a new agent for this return' radio button")
      PurchaserAgentDetailsPage.radioButton(PurchaserAgentDetailsPage.addNewAgent)
      And("User clicks save and continue")
      PurchaserAgentDetailsPage.saveAndContinue()
      Then("The user should be navigated to the Agents Name Page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      And("The user enters their purchaser Agents name")
      PurchaserAgentName.input(By.id(PurchaserAgentName.agentName), PurchaserAgentName.agentNameInput)
      And("The User selects save and continue")
      PurchaserAgentName.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Address page")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.editPageTitleAgent)
      PurchaserAgentAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      PurchaserAgentAddressPage.clickContinueButton()
      Then("The user is navigated to the Does The Purchaser Agent have Contact Details page ")
      DoesPurchaserAgentHaveContactDetailsPage.verifyPageTitle(DoesPurchaserAgentHaveContactDetailsPage.pageTitle)
      And("The user clicks no")
      DoesPurchaserAgentHaveContactDetailsPage.radioButton(DoesPurchaserAgentHaveContactDetailsPage.no)
      And("The user selects save and continue")
      DoesPurchaserAgentHaveContactDetailsPage.saveAndContinue()
      Then("User navigated to Do you want to add reference for this return page")
      PurchaserAgentAddReferencePage.verifyPageTitle(PurchaserAgentAddReferencePage.pageTitle)
      And("User selects Yes radio button")
      PurchaserAgentAddReferencePage.radioButton(PurchaserAgentAddReferencePage.yes)
      And("User clicks save and continue")
      PurchaserAgentAddReferencePage.saveAndContinue()
      Then("User is navigated to What is Agent's reference for this return page")
      PurchaserAgentReferencePage.verifyPageTitle(PurchaserAgentReferencePage.pageTitle)
      And("User enter Reference value")
      PurchaserAgentReferencePage.input(
        By.id(PurchaserAgentReferencePage.purchaserReference),
        PurchaserAgentReferencePage.purchaserReferenceInput
      )
      And("User clicks save and continue")
      PurchaserAgentReferencePage.saveAndContinue()
      Then("User Is navigated to Is Purchaser Agent authorised to have Correspondence Page")
      PurchaserAgentCorrespondencePage.verifyPageTitle(PurchaserAgentCorrespondencePage.pageTitle)
      And("The user selects the Yes Radio Button")
      PurchaserAgentCorrespondencePage.radioButton(PurchaserAgentCorrespondencePage.no)
      And("The user clicks save and continue")
      PurchaserAgentCorrespondencePage.saveAndContinue()
      Then("User should be navigated to purchaser Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for Do you want to add agent contact details")
      PurchaserAgentCheckYourAnswers.clickAddAgentContactDetailsChange()
      Then("User is navigated to Do you want to add agent contact details page")
      DoesPurchaserAgentHaveContactDetailsPage.verifyPageTitle(DoesPurchaserAgentHaveContactDetailsPage.pageTitle)
      And("The user selects yes")
      DoesPurchaserAgentHaveContactDetailsPage.radioButton(DoesPurchaserAgentHaveContactDetailsPage.yes)
      And("The user selects save and continue")
      DoesPurchaserAgentHaveContactDetailsPage.saveAndContinue()
      Then("User should be navigated to purchasers Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks on change link for add agent contact details")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentContactDetailsChange()
      Then("User should be navigated to Agent Contact Details Page")
      PurchaserAgentContactDetails.verifyPageTitle(PurchaserAgentContactDetails.pageTitle)
      When("User enters Purchaser Agent's phone number")
      PurchaserAgentContactDetails.input(
        By.id(PurchaserAgentContactDetails.emailAddress),
        PurchaserAgentContactDetails.emailAddressInput
      )
      And("User enters Purchaser Agent's email address")
      PurchaserAgentContactDetails.input(
        By.id(PurchaserAgentContactDetails.phoneNumber),
        PurchaserAgentContactDetails.phoneNumberInput
      )
      And("User clicks save and continue")
      PurchaserAgentContactDetails.saveAndContinue()
      Then("User should be navigated to purchaser Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("clicks the save and continue button")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user selects 'Yes' radio button")
      PurchaserAgentOverviewPage.radioButton(PurchaserAgentOverviewPage.yes)
      And("clicks the save and continue button")
      PurchaserAgentOverviewPage.saveAndContinue()
    }

    Scenario("Complete the Purchaser Agent Journey for additional purchaser", PurchaserAgentJourney) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("User navigated to Select Purchaser Agent Details page")
      PurchaserAgentDetailsPage.verifyPageTitle(PurchaserAgentDetailsPage.pageTitle)
      When("User selects 'Add a new agent for this return' radio button")
      PurchaserAgentDetailsPage.radioButton(PurchaserAgentDetailsPage.selectAgent)
      And("User clicks save and continue")
      PurchaserAgentDetailsPage.saveAndContinue()
      Then("User navigated to Do you want to add reference for this return page")
      PurchaserAgentAddReferencePage.verifyPageTitle(PurchaserAgentAddReferencePage.pageTitle)
      And("User selects Yes radio button")
      PurchaserAgentAddReferencePage.radioButton(PurchaserAgentAddReferencePage.yes)
      And("User clicks save and continue")
      PurchaserAgentAddReferencePage.saveAndContinue()
      Then("User is navigated to What is Agent's reference for this return page")
      PurchaserAgentReferencePage.verifyPageTitle(PurchaserAgentReferencePage.pageTitle)
      And("User enter Reference value")
      PurchaserAgentReferencePage.input(
        By.id(PurchaserAgentReferencePage.purchaserReference),
        PurchaserAgentReferencePage.purchaserReferenceInput
      )
      And("User clicks save and continue")
      PurchaserAgentReferencePage.saveAndContinue()
      Then("User Is navigated to Is Purchaser Agent authorised to have Correspondence Page")
      PurchaserAgentCorrespondencePage.verifyPageTitle(PurchaserAgentCorrespondencePage.pageTitle)
      And("The user selects the Yes Radio Button")
      PurchaserAgentCorrespondencePage.radioButton(PurchaserAgentCorrespondencePage.yes)
      And("The user clicks save and continue")
      PurchaserAgentCorrespondencePage.saveAndContinue()
      Then("User should be navigated to purchaser Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("User clicks save and continue")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Agent Journey with full purchaser with return agent stub data",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser-with-agent"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor")
      PurchaserAgentOverviewPage.clickPurchaserAgentRemove()
      Then("the user is navigated to the Remove Vendor page")
      PurchaserAgentRemovePage.verifyPageTitle(PurchaserAgentRemovePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentRemovePage.radioButton(PurchaserAgentRemovePage.yes)
      And("clicks the Save and continue button")
      PurchaserAgentRemovePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user clicks the 'Change' link to return to check your answer")
      PurchaserAgentOverviewPage.clickPurchaserAgentChange()
      Then("User should be navigated to purchaser Agent's check your answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
    }

  }
}
