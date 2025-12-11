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
import uk.gov.hmrc.ui.pages.{purchaser, *}
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.pages.purchaser.{DoesPurchaserHaveNI, DoesPurchaserHavePhoneNumber, IndividualPurchaserIDPage, PartnershipUTRPage, PurchaserAddressPage, PurchaserBeforeYouStartPage, PurchaserConfirmNameOfPurchaserPage, PurchaserConfirmPurchaserIdentityPage, PurchaserDateOfBirth, PurchaserNameOfPurchaserPage, PurchaserNationalinsuranceNumberPage, PurchaserWhoIsMakingThePurchasePage}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class PurchaserQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Purchaser Questions") {
    Scenario(
      "Complete the Purchaser Questions user journey as a Individual with prelim questions submitted stub data incomplete purchaser"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the confirm page when there isnt a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks no")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.no)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()
      Then("the user should be on who is the purchaser page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      And("the user clicks individual")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.individual)
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user should be on the What is the purchaser’s name? page")
      PurchaserNameOfPurchaserPage.verifyPageTitle(PurchaserNameOfPurchaserPage.pageTitle)
      And("the user inputs all fields")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.forename1Id), "forename1")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.forename2Id), "forename2")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.surnameId), "surname")
      PurchaserNameOfPurchaserPage.saveAndContinue()
      Then("the user should be redirected to address lookup")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitle)
      And("clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("User should be navigated to Do you want to add phone number?")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      And("user selects No on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user lands on to the Does Purchaser have NI page")
      DoesPurchaserHaveNI.verifyPageTitle(DoesPurchaserHaveNI.pageTitle)
      And("user selects Yes on the page")
      DoesPurchaserHaveNI.radioButton(DoesPurchaserHaveNI.yes)
      And("the user click Save and Continue")
      DoesPurchaserHaveNI.saveAndContinue()
      Then("the user navigates to What is Purchaser's National Insurance Page")
      PurchaserNationalinsuranceNumberPage.verifyPageTitle(PurchaserNationalinsuranceNumberPage.pageTitle)
      And("User enter a valid National Insurance Number")
      PurchaserNationalinsuranceNumberPage.input(
        By.id(PurchaserNationalinsuranceNumberPage.Nino),
        PurchaserNationalinsuranceNumberPage.NinoValue
      )
      And("the user click Save and Continue")
      PurchaserNationalinsuranceNumberPage.saveAndContinue()
      Then("User should be navigated to What is purchaser Date of Birth page")
      PurchaserDateOfBirth.verifyPageTitle(PurchaserDateOfBirth.pageTitle)
      And("User enters Purchaser's Date of Birth")
      PurchaserDateOfBirth.enterDateOfBirth()
      And("the user click Save and Continue")
      PurchaserDateOfBirth.saveAndContinue()
      // User should be navigated to "Is purchaser acting as trustee page
      // User selects no or yes and continue
      // User should be navigated to "Are purchaser and vendor connected page
      // User selects no or yes and continue
    }

    Scenario(
      "Complete the Purchaser Questions user journey as a Individual who doesn't have NI with prelim questions submitted stub data incomplete purchaser"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the confirm page when there isnt a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks no")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.no)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()
      Then("the user should be on who is the purchaser page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      And("the user clicks individual")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.individual)
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user should be on the What is the purchaser’s name? page")
      PurchaserNameOfPurchaserPage.verifyPageTitle(PurchaserNameOfPurchaserPage.pageTitle)
      And("the user inputs all fields")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.forename1Id), "forename1")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.forename2Id), "forename2")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.surnameId), "surname")
      PurchaserNameOfPurchaserPage.saveAndContinue()
      Then("the user should be redirected to address lookup")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitle)
      And("clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      // Scenario to cover individual purchaser without NI number
      Then("User should be navigated to Do you want to add phone number?")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      And("user selects No on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user lands on to the Does Purchaser have NI page")
      DoesPurchaserHaveNI.verifyPageTitle(DoesPurchaserHaveNI.pageTitle)
      And("user selects No on the page")
      DoesPurchaserHaveNI.radioButton(DoesPurchaserHaveNI.no)
      And("the user click Save and Continue")
      DoesPurchaserHaveNI.saveAndContinue()
      Then("User should be navigated to Provide a form of ID for purchaser page")
      IndividualPurchaserIDPage.verifyPageTitle(IndividualPurchaserIDPage.pageTitle)
      When("User enters ID details")
      IndividualPurchaserIDPage.input(
        By.id(IndividualPurchaserIDPage.purchaserIdNumberOrReference),
        IndividualPurchaserIDPage.idNumberOrReferenceInput
      )
      And("User enters Country Issued details")
      IndividualPurchaserIDPage.input(
        By.id(IndividualPurchaserIDPage.purchaserCountryIssued),
        IndividualPurchaserIDPage.countryIssuedInput
      )
      And("User clicks Save and Continue")
      IndividualPurchaserIDPage.saveAndContinue()

      // User should be navigated to "Is purchaser acting as trustee page"
      // User selects no or yes and continue
      // User should be navigated to "Are purchaser and vendor connected page
      // User selects no or yes and continue
    }

    Scenario(
      "Complete the Purchaser Questions user journey as a Company with VAT ID and with prelim questions submitted stub data incomplete purchaser"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the confirm page when there isnt a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks no")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.no)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()
      Then("the user should be on who is the purchaser page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      And("the user clicks company")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.company)
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user should be on the What is the purchaser’s name? page")
      PurchaserNameOfPurchaserPage.verifyPageTitle(PurchaserNameOfPurchaserPage.pageTitle)
      And("the user inputs company name")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.companyId), "Company Name")
      PurchaserNameOfPurchaserPage.saveAndContinue()
      Then("the user should be redirected to address lookup")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitleCompany)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompany)
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompany)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitleCompany)
      And("clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("User should be navigated to Do you want to add phone number?")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      And("user selects No on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      PurchaserConfirmPurchaserIdentityPage.verifyPageTitle(PurchaserConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'VAT registration number' radio button")
      PurchaserConfirmPurchaserIdentityPage.radioButton(PurchaserConfirmPurchaserIdentityPage.vatRegistrationNumber)
      And("clicks the Save and continue button")
      PurchaserConfirmPurchaserIdentityPage.saveAndContinue()
      // user navigates to enter VAT number page
      // user enters VAT number and continue
      // User should be navigated to "Is purchaser acting as trustee page
      // User selects no or yes and continue
      // User should be navigated to "Are purchaser and vendor connected page
      // User selects no or yes and continue

    }

    Scenario(
      "Complete the Purchaser Questions user journey as a Company with corporation UTR ID and with prelim questions submitted stub data incomplete purchaser yes to stub data"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the confirm page when there isnt a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks yes")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.yes)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()
      Then("the user should be redirected to address lookup")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitleCompanyStub)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompanyStub)
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompanyStub)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitleCompanyStub)
      And("clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("User should be navigated to Do you want to add phone number?")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      And("user selects No on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      PurchaserConfirmPurchaserIdentityPage.verifyPageTitle(PurchaserConfirmPurchaserIdentityPage.pageTitleStub)
      When("the user selects the 'Corporation tax Unique Taxpayer Reference (UTR)' radio button")
      PurchaserConfirmPurchaserIdentityPage.radioButton(PurchaserConfirmPurchaserIdentityPage.corporationTaxUTR)
      And("clicks the Save and continue button")
      PurchaserConfirmPurchaserIdentityPage.saveAndContinue()
      // user navigates to enter UTR details page
      // user enters UTR details and continue
      // User should be navigated to "Is purchaser acting as trustee page
      // User selects no or yes and continue
      // User should be navigated to "Are purchaser and vendor connected page
      // User selects no or yes and continue
    }

    Scenario(
      "Complete the Purchaser Questions user journey as a Company with partnership UTR ID with prelim questions submitted stub data and full purchaser"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be on who is the purchaser page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      And("the user clicks company")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.company)
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user should be on the What is the purchaser’s name? page")
      PurchaserNameOfPurchaserPage.verifyPageTitle(PurchaserNameOfPurchaserPage.pageTitle)
      And("the user inputs company name")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.companyId), "Company Name")
      PurchaserNameOfPurchaserPage.saveAndContinue()
      Then("the user should be redirected to address lookup")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitleCompany)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompany)
      And("enters their address manually")
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitleCompany)
      And("clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("User should be navigated to Do you want to add phone number?")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      And("user selects No on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      PurchaserConfirmPurchaserIdentityPage.verifyPageTitle(PurchaserConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'Partnership Unique Taxpayer Reference (UTR)' radio button")
      PurchaserConfirmPurchaserIdentityPage.radioButton(PurchaserConfirmPurchaserIdentityPage.partnershipUTR)
      And("clicks the Save and continue button")
      PurchaserConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user navigates to the Partnership UTR page")
      PartnershipUTRPage.verifyPageTitle(PartnershipUTRPage.pageTitle)
      And("user enters partnership UTR number")
      PartnershipUTRPage.input(By.id(PartnershipUTRPage.purchaserUTRReference), PartnershipUTRPage.purchaserUTRInput)
      And("clicks the Continue button")
      PartnershipUTRPage.saveAndContinue()

      // user navigates to enter partnership UTR details page
      // user enters partnership UTR number and continue
      // User should be navigated to "Is purchaser acting as trustee page
      // User selects no or yes and continue
      // User should be navigated to "Are purchaser and vendor connected page
      // User selects no or yes and continue
    }

    Scenario(
      "Complete the Purchaser Questions user journey as a Company with phone number details and prelim questions submitted stub data and full purchaser"
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be on who is the purchaser page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      And("the user clicks company")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.company)
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user should be on the What is the purchaser’s name? page")
      PurchaserNameOfPurchaserPage.verifyPageTitle(PurchaserNameOfPurchaserPage.pageTitle)
      And("the user inputs company name")
      PurchaserNameOfPurchaserPage.input(By.id(PurchaserNameOfPurchaserPage.companyId), "Company Name")
      PurchaserNameOfPurchaserPage.saveAndContinue()
      Then("the user should be redirected to address lookup")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitleCompany)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompany)
      And("enters their address manually")
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitleCompany)
      And("clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("User should be navigated to Do you want to add phone number?")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      And("user selects Yes on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.yes)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      // User should be navigated to "enter phone number for purchaser page
      // User enters phone number and continue
      // User should be navigated to "Is purchaser acting as trustee page
      // User selects no or yes and continue
      // User should be navigated to "Are purchaser and vendor connected page
      // User selects no or yes and continue

    }
  }
}
