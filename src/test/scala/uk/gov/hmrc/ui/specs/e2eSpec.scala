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
import uk.gov.hmrc.ui.pages.PrelimQuestions.{AboutTheTransactionPage, BeforeYouStartPage, CheckYourAnswersPage, IndividualOrCompanyPage, PropertyAddressPage, PurchasersNamePage}
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

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
    Scenario("Complete the end to end flow of Filing Journey") {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user should be navigated to the Before You Start page")
      BeforeYouStartPage.verifyPageTitle(BeforeYouStartPage.pageTitle)
      And("clicks the Save and continue button")
      BeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the Individual or Company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      When("the user selects the 'A Company' radio button")
      IndividualOrCompanyPage.radioButton(IndividualOrCompanyPage.company)
      And("clicks the Save and continue button")
      IndividualOrCompanyPage.saveAndContinue()
      Then("the user should be navigated to the Purchasers Name page")
      PurchasersNamePage.verifyPageTitle(PurchasersNamePage.pageTitleCompany)
      When("the user inputs their company name")
      PurchasersNamePage.input(By.id(PurchasersNamePage.companyName), "Test Company")
      And("clicks the Save and continue button")
      PurchasersNamePage.clickSubmitButton()
      Then("the user should be navigated to the Property Address page")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PropertyAddressPage.clickAddressManually()
      And("enters their address manually")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.editPageTitle)
      PropertyAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.confirmPageTitle)
      And("clicks the Confirm address button")
      PropertyAddressPage.clickContinueButton()
      Then("the user should be navigated to the About the Transaction page")
      AboutTheTransactionPage.verifyPageTitle(AboutTheTransactionPage.pageTitle)
      When("the user selects the 'F - Conveyance/transfer' radio button")
      AboutTheTransactionPage.radioButton(AboutTheTransactionPage.conveyance)
      And("clicks the Save and continue button")
      AboutTheTransactionPage.saveAndContinue()
      Then("the user should be navigated to the Check Your Answers page")
      CheckYourAnswersPage.verifyPageTitle(CheckYourAnswersPage.pageTitle)
      CheckYourAnswersPage.saveAndContinue()

      Then("the user should be navigated to the Return Task List page")
      ReturnTaskListPage.verifyPageTitle(ReturnTaskListPage.pageTitle)
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
  }
}
