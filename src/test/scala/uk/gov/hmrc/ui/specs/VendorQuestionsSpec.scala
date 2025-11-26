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
import uk.gov.hmrc.ui.pages.Vendor.RemoveVendorPage.{pageTitle, yesRadioButton}
import uk.gov.hmrc.ui.pages.Vendor.{AboutTheVendorPage, ConfirmVendorsAddressPage, DoYouKnowYourAgentReferencePage, RemoveVendorPage, VendorAgentAddressPage, VendorAgentPage, VendorAgentsNamePage, VendorBeforeYouStartPage, VendorOrCompanyNamePage, VendorOverviewPage, VendorPropertyAddressPage}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

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
    Scenario("Complete the Vendor Questions user journey as a Company with prelim questions submitted stub data") {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrl)
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user should be navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorOverviewPage.radioButton(VendorOverviewPage.yesRadioButton)
      And("clicks the Continue button")
      VendorOverviewPage.saveAndContinue()
      Then("the user should be navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.company)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user should be navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.companyNameInput),
        VendorOrCompanyNamePage.companyName
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user should be navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddressPage.verifyPageTitle(ConfirmVendorsAddressPage.pageTitle)
      When("the user selects the 'No' radio button")
      ConfirmVendorsAddressPage.radioButton(ConfirmVendorsAddressPage.noRadioButton)
      And("clicks the Save and continue button")
      ConfirmVendorsAddressPage.saveAndContinue()
      Then("the user should be navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user should be navigated to the Vendor Property Address page to 'Review and confirm the address'")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()

    }

    Scenario("Complete the Vendor Questions user journey as an Individual with prelim questions submitted stub data") {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrl)
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user should be navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorOverviewPage.radioButton(VendorOverviewPage.yesRadioButton)
      And("clicks the Continue button")
      VendorOverviewPage.saveAndContinue()
      Then("the user should be navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.individual)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user should be navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      VendorOrCompanyNamePage.input(By.id(VendorOrCompanyNamePage.forenameInput), VendorOrCompanyNamePage.forename)
      VendorOrCompanyNamePage.input(By.id(VendorOrCompanyNamePage.middlenameInput), VendorOrCompanyNamePage.middlename)
      VendorOrCompanyNamePage.input(By.id(VendorOrCompanyNamePage.surnameInput), VendorOrCompanyNamePage.surname)
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user should be navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddressPage.verifyPageTitle(ConfirmVendorsAddressPage.pageTitleIndividual)
      When("the user selects the 'Yes' radio button")
      ConfirmVendorsAddressPage.radioButton(ConfirmVendorsAddressPage.yesRadioButton)
      And("clicks the Confirm address button")
      ConfirmVendorsAddressPage.saveAndContinue()

    }

    Scenario(
      "Complete the Vendor Questions user journey for Vendor Agent with a reference number and no vendor stub data"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrlNoVendor)
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user should be navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.company)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user should be navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.companyNameInput),
        VendorOrCompanyNamePage.companyName
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user should be navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user should be navigated to the Vendor Property Address page to 'Review and confirm the address'")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user should be navigated to the Vendor Agent page")
      VendorAgentPage.verifyPageTitle(VendorAgentPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentPage.radioButton(VendorAgentPage.yesRadioButton)
      And("clicks the Save and continue button")
      VendorAgentPage.saveAndContinue()
      Then("the user should be navigated to the Vendor Agents Name page")
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentsNamePage.input(By.id("agentName"), VendorAgentsNamePage.agentName)
      And("clicks the Save and continue button")
      VendorAgentsNamePage.saveAndContinue()
      Then("the user should be navigated to the Vendor Agent Address page")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent)
      VendorAgentAddressPage.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the user should be navigated to the Vendor Agent Address page to 'Review and confirm the address'")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is on the Agent Reference Page")
      DoYouKnowYourAgentReferencePage.verifyPageTitle(DoYouKnowYourAgentReferencePage.pageTitle)
      And("user selects yes button")
      DoYouKnowYourAgentReferencePage.radioButton(DoYouKnowYourAgentReferencePage.yes)
      And("user clicks save and continue")
      DoYouKnowYourAgentReferencePage.saveAndContinue()
    }

    Scenario(
      "Complete the Vendor Questions user journey for Vendor Agent without a reference number and no vendor stub data"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrlNoVendor)
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user should be navigated to the Vendor Before You Start page")
      VendorBeforeYouStartPage.verifyPageTitle(VendorBeforeYouStartPage.pageTitle)
      When("clicks the Continue button")
      VendorBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the About the Vendor page")
      AboutTheVendorPage.verifyPageTitle(AboutTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      AboutTheVendorPage.radioButton(AboutTheVendorPage.company)
      And("clicks the Save and continue button")
      AboutTheVendorPage.saveAndContinue()
      Then("the user should be navigated to the Vendor or Company Name page")
      AboutTheVendorPage.verifyPageTitle(VendorOrCompanyNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorOrCompanyNamePage.input(
        By.id(VendorOrCompanyNamePage.companyNameInput),
        VendorOrCompanyNamePage.companyName
      )
      And("clicks the Save and continue button")
      VendorOrCompanyNamePage.saveAndContinue()
      Then("the user should be navigated to the Vendor Property Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleBusiness)
      VendorPropertyAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user should be navigated to the Vendor Property Address page to 'Review and confirm the address'")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleBusiness)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user should be navigated to the Vendor Agent page")
      VendorAgentPage.verifyPageTitle(VendorAgentPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentPage.radioButton(VendorAgentPage.yesRadioButton)
      And("clicks the Save and continue button")
      VendorAgentPage.saveAndContinue()
      Then("the user should be navigated to the Vendor Agents Name page")
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentsNamePage.input(By.id("agentName"), VendorAgentsNamePage.agentName)
      And("clicks the Save and continue button")
      VendorAgentsNamePage.saveAndContinue()
      Then("the user should be navigated to the Vendor Agent Address page")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.editPageTitleAgent)
      VendorAgentAddressPage.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the user should be navigated to the Vendor Agent Address page to 'Review and confirm the address'")
      VendorAgentAddressPage.verifyPageTitle(VendorAgentAddressPage.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is on the Agent Reference Page")
      DoYouKnowYourAgentReferencePage.verifyPageTitle(DoYouKnowYourAgentReferencePage.pageTitle)
      And("user selects yes button")
      DoYouKnowYourAgentReferencePage.radioButton(DoYouKnowYourAgentReferencePage.no)
      And("user clicks save and continue")
      DoYouKnowYourAgentReferencePage.saveAndContinue()
    }

    Scenario("Remove the Vendor with no vendor stub data") {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrlNoVendor)
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-questions")
      Then("the user should be navigated to the remove the Vendor page")
      RemoveVendorPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-vendor/remove-vendor"
      )
      And("the user is on remove the vendor page")
      RemoveVendorPage.verifyPageTitle(pageTitle)
      Then("the user clicks on yes radio button")
      RemoveVendorPage.radioButton(yesRadioButton)
      And("the user clicks on save and continue button")
      RemoveVendorPage.saveAndContinue()
    }
  }
}
