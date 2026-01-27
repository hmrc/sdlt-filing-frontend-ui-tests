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
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class VendorQuestionsSpec
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
      "Complete the Vendor Questions user journey as a Company with prelim questions submitted stub data",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor")
      VendorOverviewPage.clickRemoveVendor()
      Then("the user is navigated to the Remove Vendor page")
      RemoveVendorPage.verifyPageTitle(RemoveVendorPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      RemoveVendorPage.radioButton(RemoveVendorPage.yes)
      And("clicks the Save and continue button")
      RemoveVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorOverviewPage.radioButton(VendorOverviewPage.yes)
      And("clicks the Continue button")
      VendorOverviewPage.saveAndContinue()
      Then("the user is navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.company)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.companyName),
        VendorOrCompanyNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user is navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddressPage.verifyPageTitle(ConfirmVendorsAddressPage.pageTitle)
      When("the user selects the 'No' radio button")
      ConfirmVendorsAddressPage.radioButton(ConfirmVendorsAddressPage.no)
      And("clicks the Save and continue button")
      ConfirmVendorsAddressPage.saveAndContinue()
      Then("the user is navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Vendor Property Address page to 'Review and confirm' the address")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      When("the user clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
//      Flow to be fixed
//      Then("the user is navigated to the Vendor Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
    }

    Scenario(
      "Complete the Vendor Questions user journey as an Individual with prelim questions submitted stub data",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorOverviewPage.radioButton(VendorOverviewPage.yes)
      And("clicks the Continue button")
      VendorOverviewPage.saveAndContinue()
      Then("the user is navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.individual)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.forename),
        VendorOrCompanyNamePage.forenameInput
      )
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.middlename),
        VendorOrCompanyNamePage.middlenameInput
      )
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.surname),
        VendorOrCompanyNamePage.surnameInput
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user is navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddressPage.verifyPageTitle(ConfirmVendorsAddressPage.pageTitleIndividual)
      When("the user selects the 'Yes' radio button")
      ConfirmVendorsAddressPage.radioButton(ConfirmVendorsAddressPage.yes)
      And("clicks the Save and continue button")
      ConfirmVendorsAddressPage.saveAndContinue()
//      Flow to be fixed
//      Then("the user is navigated to the Vendor Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
    }

    Scenario(
      "Complete the Vendor Questions user journey for Vendor Agent with a reference number and no vendor stub data",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrlNoVendor)
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.company)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.companyName),
        VendorOrCompanyNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user is navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Vendor Property Address page to 'Review and confirm' the address")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      When("the user clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Vendor Agent page")
      VendorAgentPage.verifyPageTitle(VendorAgentPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentPage.radioButton(VendorAgentPage.yes)
      And("clicks the Save and continue button")
      VendorAgentPage.saveAndContinue()
//      Tests to be modfified once vendor flow is fixed
//      Then("the user is navigated to the Vendor Agents Name page")
//      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
//      When("the user inputs their Vendor Agent's name")
//      VendorAgentsNamePage.input(
//        By.id(VendorAgentsNamePage.agentName),
//        VendorAgentsNamePage.agentNameInput
//      )
//      And("clicks the Save and continue button")
//      VendorAgentsNamePage.saveAndContinue()
//      Then("the user is navigated to the Vendor Agent Address page")
//      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle)
//      When("the user clicks on the 'Enter the address manually' link")
//      VendorAgentAddressPage.clickAddressManually()
//      And("enters their address manually")
//      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent)
//      VendorAgentAddressPage.enterAddressManually("523", "AGC", "TE12 1TS")
//      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
//      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent)
//      When("the user clicks the Confirm address button")
//      VendorPropertyAddressPage.clickContinueButton()
//      Then("the user is navigated to the Do You Want To Add Contact Details for Agent page")
//      DoYouWantToAddContactDetailsPage.verifyPageTitle(DoYouWantToAddContactDetailsPage.pageTitle)
//      When("the user selects the 'Yes' radio button")
//      DoYouWantToAddContactDetailsPage.radioButton(DoYouWantToAddContactDetailsPage.yes)
//      And("clicks the Save and continue button")
//      DoYouWantToAddContactDetailsPage.saveAndContinue()
//      Then("the user is navigated to the Vendor Agent's Contact Details page")
//      VendorAgentsContactDetailsPage.verifyPageTitle(VendorAgentsContactDetailsPage.pageTitle)
//      When("the user inputs their Vendor Agent's phone number")
//      VendorAgentsContactDetailsPage.input(
//        By.id(VendorAgentsContactDetailsPage.agentPhoneNumber),
//        VendorAgentsContactDetailsPage.agentPhoneNumberInput
//      )
//      And("and inputs their Vendor Agent's email address")
//      VendorAgentsContactDetailsPage.input(
//        By.id(VendorAgentsContactDetailsPage.agentEmailAddress),
//        VendorAgentsContactDetailsPage.agentEmailAddressInput
//      )
//      And("clicks the Save and continue button")
//      VendorAgentsContactDetailsPage.saveAndContinue()
//      Then("the user is navigated to the Agent Reference Page")
//      DoYouKnowYourAgentReferencePage.verifyPageTitle(DoYouKnowYourAgentReferencePage.pageTitle)
//      And("the user selects the 'Yes' radio button")
//      DoYouKnowYourAgentReferencePage.radioButton(DoYouKnowYourAgentReferencePage.yes)
//      And("clicks the Save and continue button")
//      DoYouKnowYourAgentReferencePage.saveAndContinue()
//      Then("the user is navigated to the Agent Reference Page")
//      AgentReferenceNumberPage.verifyPageTitle(AgentReferenceNumberPage.pageTitle)
//      And("the user enters agent reference number")
//      AgentReferenceNumberPage.input(
//        By.id(AgentReferenceNumberPage.agentReference),
//        AgentReferenceNumberPage.agentReferenceNumber
//      )
//      And("the user clicks on save and continue button")
//      AgentReferenceNumberPage.saveAndContinue()
//      Then("the user is navigated to the Vendor Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
    }

    Scenario(
      "Complete the Vendor Questions user journey for Vendor Agent without a reference number and no vendor stub data",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user is navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.company)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.companyName),
        VendorOrCompanyNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user is navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Vendor Property Address page to 'Review and confirm' the address")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
//      Tests to be modfified once vendor flow is fixed
//      Then("the user is navigated to the Vendor Agent page")
//      VendorAgentPage.verifyPageTitle(VendorAgentPage.pageTitle)
//      When("the user selects the 'Yes' radio button")
//      VendorAgentPage.radioButton(VendorAgentPage.yes)
//      And("clicks the Save and continue button")
//      VendorAgentPage.saveAndContinue()
//      Then("the user is navigated to the Vendor Agents Name page")
//      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
//      When("the user inputs their Vendor Agent's name")
//      VendorAgentsNamePage.input(
//        By.id(VendorAgentsNamePage.agentName),
//        VendorAgentsNamePage.agentNameInput
//      )
//      And("clicks the Save and continue button")
//      VendorAgentsNamePage.saveAndContinue()
//      Then("the user is navigated to the Vendor Agent Address page")
//      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle)
//      When("the user clicks on the 'Enter the address manually' link")
//      VendorAgentAddressPage.clickAddressManually()
//      And("enters their address manually")
//      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent)
//      VendorAgentAddressPage.enterAddressManually("523", "AGC", "TE12 1TS")
//      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
//      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent)
//      And("clicks the Confirm address button")
//      VendorPropertyAddressPage.clickContinueButton()
//      Then("the user is navigated to the Do You Want To Add Contact Details for Agent page")
//      DoYouWantToAddContactDetailsPage.verifyPageTitle(DoYouWantToAddContactDetailsPage.pageTitle)
//      When("the user selects the 'No' radio button")
//      DoYouWantToAddContactDetailsPage.radioButton(DoYouWantToAddContactDetailsPage.no)
//      And("user clicks Save and Continue")
//      DoYouWantToAddContactDetailsPage.saveAndContinue()
//      Then("the user is navigated to the Agent Reference Page")
//      DoYouKnowYourAgentReferencePage.verifyPageTitle(DoYouKnowYourAgentReferencePage.pageTitle)
//      And("the user selects the 'No' radio button")
//      DoYouKnowYourAgentReferencePage.radioButton(DoYouKnowYourAgentReferencePage.no)
//      And("clicks the Save and continue button")
//      DoYouKnowYourAgentReferencePage.saveAndContinue()
//      Then("the user is navigated to the Vendor Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
//      When("the user clicks the 'Change' link for Vendor type")
//      VendorCheckYourAnswersPage.clickVendorTypeChange()
//      Then("the user is navigated to the About the Vendor page")
//      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
//      When("the user selects the 'An Individual' radio button")
//      AboutTheVendorPage.radioButton(AboutTheVendorPage.individual)
//      And("clicks the Save and continue button")
//      AboutTheVendorPage.saveAndContinue()
//      Then("the user is navigated to the Vendor Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
//      When("the user clicks the 'Change' link for Vendor Name")
//      VendorCheckYourAnswersPage.clickVendorNameChange()
//      Then("the user is navigated to Vendor or Company name page")
//      VendorOrCompanyNamePage.verifyPageTitle(VendorOrCompanyNamePage.pageTitle)
//      When("the user updates their surname")
//      VendorOrCompanyNamePage.vendorFullNameInput()
//      And("clicks the Save and continue button")
//      VendorOrCompanyNamePage.saveAndContinue()
//      Then("the user is navigated to the Vendor Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
//      When("the user clicks the 'Change' link for Vendor address")
//      VendorCheckYourAnswersPage.clickVendorAddressChange()
//      Then("the user is navigated to the Vendor Address page")
//      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.PageTitleIndividual)
//      When("the user clicks on the 'Enter the address manually' link")
//      VendorPropertyAddressPage.clickAddressManually()
//      And("enters their address manually")
//      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleIndividual)
//      VendorPropertyAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
//      Then("the user is navigated to the Property Address page to 'Review and confirm' the address")
//      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleIndividual)
//      And("clicks the Confirm address button")
//      VendorPropertyAddressPage.clickContinueButton()
//      Then("the user is navigated to the Check Your Answers page")
//      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
//      And("clicks the Save and continue button")
//      DoYouWantToAddContactDetailsPage.saveAndContinue()
    }
  }
}
