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

class VendorAgentQuestionsSpec
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
      "Complete the Vendor Agent journey with contact details and reference information and no vendor stub data",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Agent Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Before You Start page")
      VendorAgentBeforeYouStartPage.verifyPageTitle(VendorAgentBeforeYouStartPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentBeforeYouStartPage.radioButton(VendorAgentBeforeYouStartPage.yes)
      And("clicks the Save and continue button")
      VendorAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentNamePage.verifyPageTitle(VendorAgentNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentNamePage.input(
        By.id(VendorAgentNamePage.agentName),
        VendorAgentNamePage.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentNamePage.saveAndContinue()
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
      Then("the user is navigated to the Add Agent Contact Details page")
      AddVendorAgentContactDetailsPage.verifyPageTitle(AddVendorAgentContactDetailsPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentContactDetailsPage.radioButton(AddVendorAgentContactDetailsPage.yes)
      And("clicks the Save and continue button")
      AddVendorAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Enter Agent Contact Details page")
      VendorAgentEnterContactDetailsPage.verifyPageTitle(VendorAgentEnterContactDetailsPage.pageTitle)
      When("the user inputs their phone number")
      VendorAgentEnterContactDetailsPage.input(
        By.id(VendorAgentEnterContactDetailsPage.phoneNumber),
        VendorAgentEnterContactDetailsPage.phoneNumberInput
      )
      And("inputs their email address")
      VendorAgentEnterContactDetailsPage.input(
        By.id(VendorAgentEnterContactDetailsPage.emailAddress),
        VendorAgentEnterContactDetailsPage.emailAddressInput
      )
      And("clicks the Save and continue button")
      VendorAgentEnterContactDetailsPage.saveAndContinue()
      Then("the user is navigated to Add Agent Reference Number page")
      AddVendorAgentReferenceNumberPage.verifyPageTitle(AddVendorAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentReferenceNumberPage.radioButton(AddVendorAgentReferenceNumberPage.yes)
      And("clicks the Save and continue button")
      AddVendorAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Enter Agent Reference Number page")
      VendorAgentEnterReferenceNumberPage.verifyPageTitle(VendorAgentEnterReferenceNumberPage.pageTitle)
      When("the user enters their agent reference number")
      VendorAgentEnterReferenceNumberPage.input(
        By.id(VendorAgentEnterReferenceNumberPage.agentReference),
        VendorAgentEnterReferenceNumberPage.agentReferenceNumber
      )
      And("clicks the Save and continue button")
      VendorAgentEnterReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the Save and continue button")
      VendorAgentCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentOverviewPage.radioButton(VendorAgentOverviewPage.yes)
      And("clicks the Save and continue button")
      VendorAgentOverviewPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentNamePage.verifyPageTitle(VendorAgentNamePage.pageTitle)
    }

    Scenario(
      "Complete the Vendor Agent journey with no contact details and reference information and no vendor stub data",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Agent Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Before You Start page")
      VendorAgentBeforeYouStartPage.verifyPageTitle(VendorAgentBeforeYouStartPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentBeforeYouStartPage.radioButton(VendorAgentBeforeYouStartPage.yes)
      And("clicks the Save and continue button")
      VendorAgentBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentNamePage.verifyPageTitle(VendorAgentNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentNamePage.input(
        By.id(VendorAgentNamePage.agentName),
        VendorAgentNamePage.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentNamePage.saveAndContinue()
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
      Then("the user is navigated to the Add Agent Contact Details page")
      AddVendorAgentContactDetailsPage.verifyPageTitle(AddVendorAgentContactDetailsPage.pageTitle)
      When("the user selects the 'No' radio button")
      AddVendorAgentContactDetailsPage.radioButton(AddVendorAgentContactDetailsPage.no)
      And("clicks the Save and continue button")
      AddVendorAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to Add Agent Reference Number page")
      AddVendorAgentReferenceNumberPage.verifyPageTitle(AddVendorAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'No' radio button")
      AddVendorAgentReferenceNumberPage.radioButton(AddVendorAgentReferenceNumberPage.no)
      And("clicks the Save and continue button")
      AddVendorAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Agent name")
      VendorAgentCheckYourAnswersPage.clickVendorAgentNameChange()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentNamePage.verifyPageTitle(VendorAgentNamePage.pageTitle)
      When("the user updates their agent's name")
      VendorAgentNamePage.input(By.id(VendorAgentNamePage.agentName), VendorAgentNamePage.agentNameInput2)
      And("clicks the Save and continue button")
      VendorAgentNamePage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Agent Address")
      VendorAgentCheckYourAnswersPage.clickVendorAgentAddressChange()
      Then("the user is navigated to the Vendor Agent Address page")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle2)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddressPage.clickAddressManually()
      And("enters their updated address manually")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent2)
      VendorAgentAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent2)
      When("the user clicks the Confirm address button")
      VendorAgentAddressPage.clickContinueButton()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add contact details")
      VendorAgentCheckYourAnswersPage.clickAddAgentContactDetailsChange()
      Then("the user is navigated to the Add Agent Contact Details page")
      AddVendorAgentContactDetailsPage.verifyPageTitle(AddVendorAgentContactDetailsPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentContactDetailsPage.radioButton(AddVendorAgentContactDetailsPage.yes)
      And("clicks the Save and continue button")
      AddVendorAgentContactDetailsPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add a reference")
      VendorAgentCheckYourAnswersPage.clickDoYouWantToAddReferenceChange()
      Then("the user is navigated to Add Agent Reference Number page")
      AddVendorAgentReferenceNumberPage.verifyPageTitle(AddVendorAgentReferenceNumberPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentReferenceNumberPage.radioButton(AddVendorAgentReferenceNumberPage.yes)
      And("clicks the Save and continue button")
      AddVendorAgentReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Enter Agent Reference Number")
      VendorAgentCheckYourAnswersPage.clickVendorAgentReferenceNumberChange()
      Then("the user is navigated to the Enter Agent Reference Number page")
      VendorAgentEnterReferenceNumberPage.verifyPageTitle(VendorAgentEnterReferenceNumberPage.pageTitle)
      When("the user enters their agent reference number")
      VendorAgentEnterReferenceNumberPage.input(
        By.id(VendorAgentEnterReferenceNumberPage.agentReference),
        VendorAgentEnterReferenceNumberPage.agentReferenceNumber2
      )
      And("clicks the Save and continue button")
      VendorAgentEnterReferenceNumberPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
      When("the user clicks the Save and continue button")
      VendorAgentCheckYourAnswersPage.saveAndContinue()
      Then("User should be navigated to Vendor Agent's overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
    }

    Scenario(
      "Begin the Vendor Agent journey and remove a vendor agent with main vendor represented by agent stub data",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Agent Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor agent")
      VendorAgentOverviewPage.clickVendorAgentRemove()
      Then("the user is navigated to Are you sure you want to remove the vendor agent page")
      RemoveVendorAgentPage.verifyPageTitle(RemoveVendorAgentPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      RemoveVendorAgentPage.radioButton(RemoveVendorAgentPage.yes)
      And("clicks the Save and continue button")
      RemoveVendorAgentPage.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverviewPage.verifyPageTitle(VendorAgentOverviewPage.pageTitle)
      When("the user clicks the 'Change' link to amend a vendor agent's details")
      VendorAgentOverviewPage.clickVendorAgentChange()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswersPage.verifyPageTitle(VendorAgentCheckYourAnswersPage.pageTitle)
    }
  }
}
