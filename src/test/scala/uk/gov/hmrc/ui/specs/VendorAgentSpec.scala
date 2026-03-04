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
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.pages.VendorAgent.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class VendorAgentSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Vendor Questions") {
    Scenario(
      "Complete the Vendor Agent user journey with contact details and reference information",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("The user navigates to the vendor agent before you start page")
      VendorAgentBeforeYouStartPage.verifyPageTitle(VendorAgentBeforeYouStartPage.pageTitle)
      And("The user clicks the yes radio button")
      VendorAgentBeforeYouStartPage.radioButton(VendorAgentBeforeYouStartPage.yes)
      And("The user selects save and continue")
      VendorAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agents Name page")
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentsNamePage.input(
        By.id(VendorAgentsNamePage.agentName),
        VendorAgentsNamePage.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentsNamePage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Address page")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent)
      VendorAgentAddressPage.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Do You Want To Add Contact Details for Agent page")
      DoYouWantToAddContactDetailsPage.verifyPageTitle(DoYouWantToAddContactDetailsPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      DoYouWantToAddContactDetailsPage.radioButton(DoYouWantToAddContactDetailsPage.yes)
      And("the user clicks the Save and continue button")
      DoYouWantToAddContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Enter Contact Details for Agent page")
      VendorAgentContactDetailsPage.verifyPageTitle(VendorAgentContactDetailsPage.pageTitle)
      When("the user inputs their phone number details")
      VendorAgentContactDetailsPage.input(
        By.id(VendorAgentContactDetailsPage.phoneNumber),
        VendorAgentContactDetailsPage.phoneNumberInput
      )
      And("the user inputs their email address details")
      VendorAgentContactDetailsPage.input(
        By.id(VendorAgentContactDetailsPage.emailAddress),
        VendorAgentContactDetailsPage.emailAddressInput
      )
      And("user clicks Save and Continue")
      VendorAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to Do you want to add a reference for this return page")
      DoYouWantToAddAgentReferencePage.verifyPageTitle(DoYouWantToAddAgentReferencePage.pageTitle)
      And("the user selects the 'Yes' radio button")
      DoYouWantToAddAgentReferencePage.radioButton(DoYouWantToAddAgentReferencePage.yes)
      And("clicks the Save and continue button")
      DoYouWantToAddAgentReferencePage.saveAndContinue()
      Then("the user is navigated to the Agent Reference Page")
      AgentReferenceNumberPage.verifyPageTitle(AgentReferenceNumberPage.pageTitle)
      And("the user enters agent reference number")
      AgentReferenceNumberPage.input(
        By.id(AgentReferenceNumberPage.agentReference),
        AgentReferenceNumberPage.agentReferenceNumber
      )
      And("the user clicks on save and continue button")
      AgentReferenceNumberPage.saveAndContinue()
      Then("User should be navigated to Vendor Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      And("User clicks on save and continue button")
      VendorAgentCheckYourAnswersPage.saveAndContinue()
      Then("User should be navigated to Vendor Agent's overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      And("user clicks on yes for the question 'Do you want to add an agent for vendor?")
      VendorAgentOverviewPage.radioButton(VendorAgentOverviewPage.yes)
      And("User clicks on save and continue button")
      VendorAgentOverviewPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agents Name page")
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
    }

    Scenario(
      "Complete the Vendor Agent user journey without contact details and reference information",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("The user navigates to the vendor agent before you start page")
      VendorAgentBeforeYouStartPage.verifyPageTitle(VendorAgentBeforeYouStartPage.pageTitle)
      And("The user clicks the yes radio button")
      VendorAgentBeforeYouStartPage.radioButton(VendorAgentBeforeYouStartPage.yes)
      And("The user selects save and continue")
      VendorAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agents Name page")
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentsNamePage.input(
        By.id(VendorAgentsNamePage.agentName),
        VendorAgentsNamePage.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentsNamePage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Address page")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent)
      VendorAgentAddressPage.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent)
      When("the user clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Do You Want To Add Contact Details for Agent page")
      DoYouWantToAddContactDetailsPage.verifyPageTitle(DoYouWantToAddContactDetailsPage.pageTitle)
      When("the user selects the 'No' radio button")
      DoYouWantToAddContactDetailsPage.radioButton(DoYouWantToAddContactDetailsPage.no)
      And("clicks the Save and continue button")
      DoYouWantToAddContactDetailsPage.saveAndContinue()
      Then("the user is navigated to Do you want to add a reference for this return page")
      DoYouWantToAddAgentReferencePage.verifyPageTitle(DoYouWantToAddAgentReferencePage.pageTitle)
      And("the user selects the 'No' radio button")
      DoYouWantToAddAgentReferencePage.radioButton(DoYouWantToAddAgentReferencePage.no)
      And("clicks the Save and continue button")
      DoYouWantToAddAgentReferencePage.saveAndContinue()
      Then("User should be navigated to Vendor Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      And("User clicks on change link for Vendor Agent's name")
      VendorAgentCheckYourAnswersPage.clickVendorAgentNameChange()
      Then("User should be navigated to Vendor Agent's name page")
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      And("User updates agent name details")
      VendorAgentsNamePage.input(By.id(VendorAgentsNamePage.agentName), VendorAgentsNamePage.agentNameInput2)
      Then("User clicks on save and continue")
      VendorAgentsNamePage.saveAndContinue()
      And("User should be navigated to Vendor Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      Then("User clicks on change link for Vendor Agent's address")
      VendorAgentCheckYourAnswersPage.clickVendorAgentAddressChange()
      And("User should be navigated to Vendor Agent's address page")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle2)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddressPage.clickAddressManually()
      And("enters their updated address manually")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent2)
      VendorAgentAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent2)
      And("User clicks on save and continue")
      VendorAgentAddressPage.clickContinueButton()
      Then("User should be navigated to Vendors Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      And("User clicks on change link for Do you want to add agent contact details")
      VendorAgentCheckYourAnswersPage.clickAddAgentContactDetailsChange()
      Then("User is navigated to Do you want to add agent contact details page")
      DoYouWantToAddContactDetailsPage.verifyPageTitle(DoYouWantToAddContactDetailsPage.pageTitle)
      And("The user selects Yes")
      DoYouWantToAddContactDetailsPage.radioButton(DoYouWantToAddContactDetailsPage.yes)
      And("The user selects save and continue")
      DoYouWantToAddContactDetailsPage.saveAndContinue()
      Then("User should be navigated to Vendors Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      And("User clicks on change link for Do you want to add Reference Number")
      VendorAgentCheckYourAnswersPage.clickDoYouWantToAddReferenceChange()
      Then("User navigated to Do you want to add reference for this return page")
      DoYouWantToAddAgentReferencePage.verifyPageTitle(DoYouWantToAddAgentReferencePage.pageTitle)
      And("User selects Yes radio button")
      DoYouWantToAddAgentReferencePage.radioButton(DoYouWantToAddAgentReferencePage.yes)
      And("User clicks save and continue")
      DoYouWantToAddAgentReferencePage.saveAndContinue()
      Then("User should be navigated to Vendors Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      And("User clicks on change link for enter agent reference number")
      VendorAgentCheckYourAnswersPage.clickVendorAgentReferenceNumberChange()
      Then("User is navigated to What is Agent's reference for this return page")
      AgentReferenceNumberPage.verifyPageTitle(AgentReferenceNumberPage.pageTitle)
      And("User enter Reference value")
      AgentReferenceNumberPage.input(
        By.id(AgentReferenceNumberPage.agentReference),
        AgentReferenceNumberPage.agentReferenceNumber2
      )
      And("User clicks save and continue")
      AgentReferenceNumberPage.saveAndContinue()
      Then("User should be navigated to Vendors Agent's check your answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      And("User clicks on save and continue button")
      VendorAgentCheckYourAnswersPage.saveAndContinue()
      Then("User should be navigated to Vendor Agent's overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      And("user clicks on no for the question 'Do you want to add an agent for vendor?")
      VendorAgentOverviewPage.radioButton(VendorAgentOverviewPage.no)
      And("User clicks on save and continue button")
      VendorAgentOverviewPage.saveAndContinue()
    }

    Scenario(
      "Removing vendor agent",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor agent")
      VendorAgentOverviewPage.clickVendorAgentRemove()
      Then("the user is navigated to Are you sure you want to remove the vendor agent page")
      VendorAgentRemovePage.verifyPageTitle(VendorAgentRemovePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentRemovePage.radioButton(VendorAgentRemovePage.yes)
      And("clicks the Save and continue button")
      VendorAgentRemovePage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      When("the user clicks the 'Change' link to amend a vendor agent's details")
      VendorAgentOverviewPage.clickVendorAgentChange()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
    }
  }
}
