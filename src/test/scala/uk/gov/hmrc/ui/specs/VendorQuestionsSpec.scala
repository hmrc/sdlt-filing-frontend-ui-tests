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
      "Complete the Vendor Questions user journey as a Company with main vendor represented by agent stub data",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-questions")
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
      Then("the user is navigated to the Who Is The Vendor page")
      WhoIsTheVendorPage.verifyPageTitle(WhoIsTheVendorPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      WhoIsTheVendorPage.radioButton(WhoIsTheVendorPage.company)
      And("clicks the Save and continue button")
      WhoIsTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor Name page")
      WhoIsTheVendorPage.verifyPageTitle(VendorNamePage.pageTitleCompany)
      When("the user inputs their company name")
      VendorNamePage.input(
        By.id(VendorNamePage.companyName),
        VendorNamePage.companyNameInput
      )
      And("clicks the Save and continue button")
      VendorNamePage.saveAndContinue()
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
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Vendor type")
      VendorCheckYourAnswersPage.clickVendorTypeChange()
      Then("the user is navigated to the Who Is The Vendor page")
      WhoIsTheVendorPage.verifyPageTitle(WhoIsTheVendorPage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      WhoIsTheVendorPage.radioButton(WhoIsTheVendorPage.individual)
      And("clicks the Save and continue button")
      WhoIsTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Vendor Name")
      VendorCheckYourAnswersPage.clickVendorNameChange()
      Then("the user is navigated to Vendor Name page")
      VendorNamePage.verifyPageTitle(VendorNamePage.pageTitle)
      When("the user updates their surname")
      VendorNamePage.vendorFullNameInput()
      And("clicks the Save and continue button")
      VendorNamePage.saveAndContinue()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
      When("the user clicks the 'Change' link for Vendor address")
      VendorCheckYourAnswersPage.clickVendorAddressChange()
      Then("the user is navigated to the Vendor Address page")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.PageTitleIndividual)
      When("the user clicks on the 'Enter the address manually' link")
      VendorPropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.editPageTitleIndividual)
      VendorPropertyAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Property Address page to 'Review and confirm' the address")
      VendorPropertyAddressPage.verifyPageTitle(VendorPropertyAddressPage.confirmPageTitleIndividual)
      And("clicks the Confirm address button")
      VendorPropertyAddressPage.clickContinueButton()
      Then("the user is navigated to the Check Your Answers page")
      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
      When("the user clicks the Save and continue button")
      VendorCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Vendor Overview page")
      VendorOverviewPage.verifyPageTitle(VendorOverviewPage.pageTitle)
    }

    Scenario(
      "Complete the Vendor Questions user journey as an Individual with main vendor represented by agent stub data",
      VendorJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-vendor-questions")
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
      Then("the user is navigated to the Who Is The Vendor page")
      WhoIsTheVendorPage.verifyPageTitle(WhoIsTheVendorPage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      WhoIsTheVendorPage.radioButton(WhoIsTheVendorPage.individual)
      And("clicks the Save and continue button")
      WhoIsTheVendorPage.saveAndContinue()
      Then("the user is navigated to the Vendor Name page")
      WhoIsTheVendorPage.verifyPageTitle(VendorNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      VendorNamePage.input(
        By.id(VendorNamePage.forename),
        VendorNamePage.forenameInput
      )
      VendorNamePage.input(
        By.id(VendorNamePage.middlename),
        VendorNamePage.middlenameInput
      )
      VendorNamePage.input(
        By.id(VendorNamePage.surname),
        VendorNamePage.surnameInput
      )
      And("clicks the Save and continue button")
      VendorNamePage.saveAndContinue()
      Then("the user is navigated to the Confirm Vendors Address page")
      ConfirmVendorsAddressPage.verifyPageTitle(ConfirmVendorsAddressPage.pageTitleIndividual)
      When("the user selects the 'Yes' radio button")
      ConfirmVendorsAddressPage.radioButton(ConfirmVendorsAddressPage.yes)
      And("clicks the Save and continue button")
      ConfirmVendorsAddressPage.saveAndContinue()
      Then("the user is navigated to the Vendor Check Your Answers page")
      VendorCheckYourAnswersPage.verifyPageTitle(VendorCheckYourAnswersPage.pageTitle)
    }
  }
}
