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
    Scenario(
      "Complete the Purchaser Agent journey with no purchaser agents stub data",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the Continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Select Purchaser Agent page")
      SelectPurchaserAgentPage.verifyPageTitle(SelectPurchaserAgentPage.pageTitle)
      When("the user selects 'Add a new agent for this return' radio button")
      SelectPurchaserAgentPage.radioButton(SelectPurchaserAgentPage.addNewAgent)
      And("clicks the Save and continue button")
      SelectPurchaserAgentPage.saveAndContinue()
      Then("the user is navigated to the Agent Name page")
      PurchaserAgentNamePage.verifyPageTitle(PurchaserAgentNamePage.pageTitle)
      When("the user enters their Purchaser Agents name")
      PurchaserAgentNamePage.input(By.id(PurchaserAgentNamePage.agentName), PurchaserAgentNamePage.agentNameInput)
      And("clicks the Save and continue button")
      PurchaserAgentNamePage.saveAndContinue()
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
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetailsPage.verifyPageTitle(AddPurchaserAgentContactDetailsPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentContactDetailsPage.radioButton(AddPurchaserAgentContactDetailsPage.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Enter Agent Contact Details Page")
      PurchaserAgentEnterContactDetailsPage.verifyPageTitle(PurchaserAgentEnterContactDetailsPage.pageTitle)
      When("the user enters their Purchaser Agent's phone number")
      PurchaserAgentEnterContactDetailsPage.input(
        By.id(PurchaserAgentEnterContactDetailsPage.emailAddress),
        PurchaserAgentEnterContactDetailsPage.emailAddressInput
      )
      And("enters their Purchaser Agent's email address")
      PurchaserAgentEnterContactDetailsPage.input(
        By.id(PurchaserAgentEnterContactDetailsPage.phoneNumber),
        PurchaserAgentEnterContactDetailsPage.phoneNumberInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumberPage.verifyPageTitle(AddPurchaserAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserAgentReferenceNumberPage.radioButton(AddPurchaserAgentReferenceNumberPage.no)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondencePage.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondencePage.pageTitle
      )
      When("the user selects the 'Yes' radio button")
      PurchaserAgentAuthorisedForCorrespondencePage.radioButton(PurchaserAgentAuthorisedForCorrespondencePage.yes)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondencePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser Agent's name")
      PurchaserAgentCheckYourAnswersPage.clickPurchaserAgentNameChange()
      Then("the user is navigated to the Purchaser Agent name page")
      PurchaserAgentNamePage.verifyPageTitle(PurchaserAgentNamePage.pageTitle)
      When("the user updates their agent name details")
      PurchaserAgentNamePage.input(By.id(PurchaserAgentNamePage.agentName), PurchaserAgentNamePage.agentNameInput2)
      And("clicks the Save and continue button")
      PurchaserAgentNamePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser Agent's address")
      PurchaserAgentCheckYourAnswersPage.clickPurchaserAgentAddressChange()
      Then("the user is navigated to the Purchaser Agent's address page")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.pageTitle2)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddressPage.clickAddressManually()
      And("enters their updated address manually")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.editPageTitleAgent2)
      PurchaserAgentAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.confirmPageTitleAgent2)
      And("clicks the Save and continue button")
      PurchaserAgentAddressPage.clickContinueButton()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add agent contact details")
      PurchaserAgentCheckYourAnswersPage.clickAddAgentContactDetailsChange()
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetailsPage.verifyPageTitle(AddPurchaserAgentContactDetailsPage.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserAgentContactDetailsPage.radioButton(AddPurchaserAgentContactDetailsPage.no)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add a Reference Number")
      PurchaserAgentCheckYourAnswersPage.clickDoYouWantToAddReferenceChange()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumberPage.verifyPageTitle(AddPurchaserAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentReferenceNumberPage.radioButton(AddPurchaserAgentReferenceNumberPage.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Enter Agent Reference Number")
      PurchaserAgentCheckYourAnswersPage.clickPurchaserAgentReferenceNumberChange()
      Then("the user is navigated to Enter Agent Reference Number page")
      PurchaserAgentEnterReferenceNumberPage.verifyPageTitle(PurchaserAgentEnterReferenceNumberPage.pageTitle)
      When("the user enters their Agent Reference Number")
      PurchaserAgentEnterReferenceNumberPage.input(
        By.id(PurchaserAgentEnterReferenceNumberPage.purchaserReference),
        PurchaserAgentEnterReferenceNumberPage.purchaserReferenceInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Is Agent authorised for Correspondence")
      PurchaserAgentCheckYourAnswersPage.clickIsAgentAuthorizedForCorrespondenceChange()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondencePage.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondencePage.pageTitle
      )
      When("the user selects the 'No' radio button")
      PurchaserAgentAuthorisedForCorrespondencePage.radioButton(PurchaserAgentAuthorisedForCorrespondencePage.no)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondencePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      And("clicks the save and continue button")
      PurchaserAgentCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user selects 'Yes' radio button")
      PurchaserAgentOverviewPage.radioButton(PurchaserAgentOverviewPage.yes)
      And("clicks the save and continue button")
      PurchaserAgentOverviewPage.saveAndContinue()
    }

    Scenario(
      "Complete the Purchaser Agent Journey with no contact details and no purchaser agents stub data",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the Continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Select Purchaser Agent page")
      SelectPurchaserAgentPage.verifyPageTitle(SelectPurchaserAgentPage.pageTitle)
      When("the user selects 'Add a new agent for this return' radio button")
      SelectPurchaserAgentPage.radioButton(SelectPurchaserAgentPage.addNewAgent)
      And("clicks the Save and continue button")
      SelectPurchaserAgentPage.saveAndContinue()
      Then("the user is navigated to the Agent Name page")
      PurchaserAgentNamePage.verifyPageTitle(PurchaserAgentNamePage.pageTitle)
      When("the user enters their Purchaser Agents name")
      PurchaserAgentNamePage.input(By.id(PurchaserAgentNamePage.agentName), PurchaserAgentNamePage.agentNameInput)
      And("clicks the Save and continue button")
      PurchaserAgentNamePage.saveAndContinue()
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
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetailsPage.verifyPageTitle(AddPurchaserAgentContactDetailsPage.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserAgentContactDetailsPage.radioButton(AddPurchaserAgentContactDetailsPage.no)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumberPage.verifyPageTitle(AddPurchaserAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentReferenceNumberPage.radioButton(AddPurchaserAgentReferenceNumberPage.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to Enter Agent Reference Number page")
      PurchaserAgentEnterReferenceNumberPage.verifyPageTitle(PurchaserAgentEnterReferenceNumberPage.pageTitle)
      When("the user enters their Agent Reference Number")
      PurchaserAgentEnterReferenceNumberPage.input(
        By.id(PurchaserAgentEnterReferenceNumberPage.purchaserReference),
        PurchaserAgentEnterReferenceNumberPage.purchaserReferenceInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondencePage.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondencePage.pageTitle
      )
      When("the user selects the 'Yes' radio button")
      PurchaserAgentAuthorisedForCorrespondencePage.radioButton(PurchaserAgentAuthorisedForCorrespondencePage.no)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondencePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add agent contact details")
      PurchaserAgentCheckYourAnswersPage.clickAddAgentContactDetailsChange()
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetailsPage.verifyPageTitle(AddPurchaserAgentContactDetailsPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentContactDetailsPage.radioButton(AddPurchaserAgentContactDetailsPage.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Enter Purchaser Agent Contact Details")
      PurchaserAgentCheckYourAnswersPage.clickPurchaserAgentContactDetailsChange()
      Then("the user is navigated to the Enter Agent Contact Details Page")
      PurchaserAgentEnterContactDetailsPage.verifyPageTitle(PurchaserAgentEnterContactDetailsPage.pageTitle)
      When("the user enters their Purchaser Agent's phone number")
      PurchaserAgentEnterContactDetailsPage.input(
        By.id(PurchaserAgentEnterContactDetailsPage.emailAddress),
        PurchaserAgentEnterContactDetailsPage.emailAddressInput
      )
      And("enters their Purchaser Agent's email address")
      PurchaserAgentEnterContactDetailsPage.input(
        By.id(PurchaserAgentEnterContactDetailsPage.phoneNumber),
        PurchaserAgentEnterContactDetailsPage.phoneNumberInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      And("clicks the save and continue button")
      PurchaserAgentCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user selects 'Yes' radio button")
      PurchaserAgentOverviewPage.radioButton(PurchaserAgentOverviewPage.yes)
      And("clicks the save and continue button")
      PurchaserAgentOverviewPage.saveAndContinue()
    }

    Scenario(
      "Complete the Purchaser Agent Journey for an existing agent with no purchaser agents stub data",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the Continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Select Purchaser Agent page")
      SelectPurchaserAgentPage.verifyPageTitle(SelectPurchaserAgentPage.pageTitle)
      When("the user selects 'Smith & Partners LLP, London' radio button")
      SelectPurchaserAgentPage.radioButton(SelectPurchaserAgentPage.selectAgent)
      And("clicks the Save and continue button")
      SelectPurchaserAgentPage.saveAndContinue()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumberPage.verifyPageTitle(AddPurchaserAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentReferenceNumberPage.radioButton(AddPurchaserAgentReferenceNumberPage.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to Enter Agent Reference Number page")
      PurchaserAgentEnterReferenceNumberPage.verifyPageTitle(PurchaserAgentEnterReferenceNumberPage.pageTitle)
      When("the user enters their Agent Reference Number")
      PurchaserAgentEnterReferenceNumberPage.input(
        By.id(PurchaserAgentEnterReferenceNumberPage.purchaserReference),
        PurchaserAgentEnterReferenceNumberPage.purchaserReferenceInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondencePage.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondencePage.pageTitle
      )
      When("the user selects the 'Yes' radio button")
      PurchaserAgentAuthorisedForCorrespondencePage.radioButton(PurchaserAgentAuthorisedForCorrespondencePage.yes)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondencePage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
      And("clicks the Save and continue button")
      PurchaserAgentCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Agent Journey with a completed purchaser agent and full purchaser with agent stub data",
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
      RemovePurchaserAgentPage.verifyPageTitle(RemovePurchaserAgentPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      RemovePurchaserAgentPage.radioButton(RemovePurchaserAgentPage.yes)
      And("clicks the Save and continue button")
      RemovePurchaserAgentPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverviewPage.verifyPageTitle(PurchaserAgentOverviewPage.pageTitle)
      When("the user clicks the 'Change' link")
      PurchaserAgentOverviewPage.clickPurchaserAgentChange()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswersPage.verifyPageTitle(PurchaserAgentCheckYourAnswersPage.pageTitle)
    }
  }
}
