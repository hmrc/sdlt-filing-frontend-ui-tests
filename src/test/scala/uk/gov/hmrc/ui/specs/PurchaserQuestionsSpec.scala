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
import uk.gov.hmrc.ui.pages.purchaser.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

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
      "Complete the Main Purchaser Questions user journey as a Individual with prelim questions submitted stub data no purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      /*Then("the user should be navigated to the confirm page when there isn't a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks yes")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.yes)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()*/
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
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      PurchaserActingAsATrusteePage.verifyPageTitle(PurchaserActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserActingAsATrusteePage.radioButton(PurchaserActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      PurchaserActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      // uncomment next two lines when navigation to the next page is ready
//      And("clicks the Save and continue button")
//      PurchaserAndVendorConnectedPage.saveAndContinue()

      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser name
      // User should be navigated to purchaser name page
      // User updates purchaser name details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser NI number
      // User should be navigated to purchaser NI number page
      // User updates purchaser NI number details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser date of birth
      // User should be navigated to purchaser date of birth page
      // User updates purchaser date of birth details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on save and continue

      Then("the user is navigated to the Purchaser Overview page")
      // Remove the line below once navigation from the previous page is complete
      PurchaserOverviewPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview"
      )
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Return Tasklist page")
    }

    Scenario(
      "Complete the Main Purchaser Questions user journey as a Individual who doesn't have NI with prelim questions submitted stub data no purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      /* Then("the user should be navigated to the confirm page when there isn't a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks no")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.no)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()*/
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
      And("user selects Yes on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.yes)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("user navigates to enter phone number for purchaser page")
      PurchaserContactPhoneNumber.verifyPageTitle(PurchaserContactPhoneNumber.pageTitle)
      And("User enters phone number")
      PurchaserContactPhoneNumber.input(
        By.id(PurchaserContactPhoneNumber.phoneNumberInputField),
        PurchaserContactPhoneNumber.phoneNumberValue
      )
      And("user clicks Save and Continue")
      PurchaserContactPhoneNumber.saveAndContinue()
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
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      PurchaserActingAsATrusteePage.verifyPageTitle(PurchaserActingAsATrusteePage.pageTitle)
      When("the user selects the 'No' radio button")
      PurchaserActingAsATrusteePage.radioButton(PurchaserActingAsATrusteePage.no)
      And("clicks the Save and continue button")
      PurchaserActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      // uncomment next two lines when navigation to the next page is ready
//      And("clicks the Save and continue button")
//      PurchaserAndVendorConnectedPage.saveAndContinue()

      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser address
      // User should be navigated to purchaser address page
      // User updates purchaser address details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser phone number
      // User should be navigated to purchaser phone number page
      // User updates purchaser phone number details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser ID details
      // User should be navigated to purchaser ID details page
      // User updates purchaser ID details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on save and continue

      Then("the user is navigated to the Purchaser Overview page")
      // Remove the line below once navigation from the previous page is complete
      PurchaserOverviewPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview"
      )
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Return Tasklist page")
    }

    Scenario(
      "Complete the Additional Purchaser Questions user journey as a Individual",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserOverviewPage.radioButton(PurchaserOverviewPage.yes)
      And("clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      // User should be navigated to is XXX the surname of the purchaser page
      // User clicks No radio button
      // User clicks Save and Continue
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
      And("user selects Yes on the page")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.yes)
      And("user clicks Save and Continue")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("user navigates to enter phone number for purchaser page")
      PurchaserContactPhoneNumber.verifyPageTitle(PurchaserContactPhoneNumber.pageTitle)
      And("User enters phone number")
      PurchaserContactPhoneNumber.input(
        By.id(PurchaserContactPhoneNumber.phoneNumberInputField),
        PurchaserContactPhoneNumber.phoneNumberValue
      )
      And("user clicks Save and Continue")
      PurchaserContactPhoneNumber.saveAndContinue()
      // User is navigated to CYA page
    }

    Scenario(
      "Complete the Main Purchaser Questions user journey as a Company with VAT ID and with prelim questions submitted stub data no purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      /*Then("the user should be navigated to the confirm page when there isn't a full purchaser (no address line 1)")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks yes")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.no)
      PurchaserConfirmNameOfPurchaserPage.saveAndContinue()*/
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
      Then("the user navigates to VAT Registration number page")
      VATRegistrationNumberPage.verifyPageTitle(VATRegistrationNumberPage.pageTitle)
      And("user enters VAT registration number")
      VATRegistrationNumberPage.input(By.id(VATRegistrationNumberPage.vat), VATRegistrationNumberPage.VATNumber)
      And("user clicks the Continue button")
      VATRegistrationNumberPage.saveAndContinue()
      Then("the user navigates to What type of company page?")
      WhatTypeOfCompany.verifyPageTitle(WhatTypeOfCompany.pageTitle)
      And("user selects 4 types of company")
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Bank, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Building_Association, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Insurance_Assurance_company, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Property_company, true)
      And("user clicks the Continue button")
      WhatTypeOfCompany.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      PurchaserActingAsATrusteePage.verifyPageTitle(PurchaserActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserActingAsATrusteePage.radioButton(PurchaserActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      PurchaserActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      // uncomment next two lines when navigation to the next page is ready
//      And("clicks the Save and continue button")
//      PurchaserAndVendorConnectedPage.saveAndContinue()

      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser name
      // User should be navigated to purchaser name page
      // User updates purchaser name details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser VAT number
      // User should be navigated to purchaser VAT number page
      // User updates purchaser VAT number details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on save and continue

      Then("the user is navigated to the Purchaser Overview page")
      // Remove the line below once navigation from the previous page is complete
      PurchaserOverviewPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview"
      )
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Return Tasklist page")

      // User should be navigated to purchaser overview page
      // User selects no to add another purchaser
      // User should be navigated to return tasklist page
    }

    Scenario(
      "Complete the Purchaser Questions user journey as a Company with corporation UTR ID and with prelim questions submitted stub data incomplete purchaser yes to stub data",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserOverviewPage.radioButton(PurchaserOverviewPage.yes)
      And("clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the confirm page the company name of the purchaser")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks yes")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.yes)
      And("clicks Save and Continue")
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
      Then("the user is navigated to the Corporation Tax UTR page")
      PurchaserCorporationTaxUTRPage.verifyPageTitle(PurchaserCorporationTaxUTRPage.pageTitle)
      When("the user inputs their company's Corporation tax Unique Taxpayer Reference (UTR)")
      PurchaserCorporationTaxUTRPage.input(
        By.id(PurchaserCorporationTaxUTRPage.corporationTaxUTR),
        PurchaserCorporationTaxUTRPage.corporationTaxUTRInput
      )
      And("clicks the Save and continue button")
      PurchaserCorporationTaxUTRPage.saveAndContinue()
      Then("the user navigates to What type of company page?")
      WhatTypeOfCompany.verifyPageTitle(WhatTypeOfCompany.pageTitle)
      And("user selects 3 types of company")
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Public_corporation, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Central_Government, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Other_financial_institute, true)
      And("user clicks the Continue button")
      WhatTypeOfCompany.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      PurchaserActingAsATrusteePage.verifyPageTitle(PurchaserActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserActingAsATrusteePage.radioButton(PurchaserActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      PurchaserActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      // uncomment next two lines when navigation to the next page is ready
//      And("clicks the Save and continue button")
//      PurchaserAndVendorConnectedPage.saveAndContinue()

      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser Identity
      // User should be navigated to purchaser Identity page
      // User updates purchaser Identity details to partnership UTR
      // User clicks on save and continue
      // User should be navigated to enter partnership UTR page
      // User enters partnership UTR details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on save and continue

      Then("the user is navigated to the Purchaser Overview page")
      // Remove the line below once navigation from the previous page is complete
      PurchaserOverviewPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview"
      )
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser")
      PurchaserOverviewPage.clickPurchaserChange()

      // User should be navigated to purchaser overview page
      // User selects change purchaser
    }

    Scenario(
      "Complete the Main Purchaser Questions user journey as a Company with partnership UTR ID with prelim questions submitted stub data and full purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
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
      Then("the user navigates to What type of company page?")
      WhatTypeOfCompany.verifyPageTitle(WhatTypeOfCompany.pageTitle)
      And("user selects 3 types of company")
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Local_authority, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Unincorporated_sole_trader_other_than_builder, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Superannuation_or_pension_fund, true)
      And("user clicks the Continue button")
      WhatTypeOfCompany.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      PurchaserActingAsATrusteePage.verifyPageTitle(PurchaserActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserActingAsATrusteePage.radioButton(PurchaserActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      PurchaserActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      // uncomment next two lines when navigation to the next page is ready
      //      And("clicks the Save and continue button")
      //      PurchaserAndVendorConnectedPage.saveAndContinue()

      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser phone number details
      // User should be navigated to purchaser phone number details page
      // User updates yes for phone number
      // User enters phone number details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for type of company
      // User should be navigated to type of company page
      // User updates type of company details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on save and continue

      Then("the user is navigated to the Purchaser Overview page")
      // Remove the line below once navigation from the previous page is complete
      PurchaserOverviewPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview"
      )
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Return Tasklist page")

      // User should be navigated to purchaser overview page
      // User selects no to add another purchaser
      // User should be navigated to return tasklist page
    }

    Scenario(
      "Complete the Main Purchaser Questions user journey as a Company, providing phone number details along with another form of ID and prelim questions submitted stub data and full purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
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
      Then("user navigates to enter phone number for purchaser page")
      PurchaserContactPhoneNumber.verifyPageTitle(PurchaserContactPhoneNumber.pageTitle)
      And("User enters phone number")
      PurchaserContactPhoneNumber.input(
        By.id(PurchaserContactPhoneNumber.phoneNumberInputField),
        PurchaserContactPhoneNumber.phoneNumberValue
      )
      And("user clicks Save and Continue")
      PurchaserContactPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      PurchaserConfirmPurchaserIdentityPage.verifyPageTitle(PurchaserConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'Another form of ID' radio button")
      PurchaserConfirmPurchaserIdentityPage.radioButton(PurchaserConfirmPurchaserIdentityPage.anotherFormOfID)
      And("clicks the Save and continue button")
      PurchaserConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the Form of ID Company page")
      PurchaserFormOfIDCompanyPage.verifyPageTitle(PurchaserFormOfIDCompanyPage.pageTitle)
      When("the user inputs their ID number or reference")
      PurchaserFormOfIDCompanyPage.input(
        By.id(PurchaserFormOfIDCompanyPage.idNumberOrReference),
        PurchaserFormOfIDCompanyPage.idNumberOrReferenceInput
      )
      And("inputs their Country of issue")
      PurchaserFormOfIDCompanyPage.input(
        By.id(PurchaserFormOfIDCompanyPage.countryOfIssue),
        PurchaserFormOfIDCompanyPage.countryOfIssueInput
      )
      And("clicks the Save and continue button")
      PurchaserFormOfIDCompanyPage.saveAndContinue()
      Then("the user navigates to What type of company page?")
      WhatTypeOfCompany.verifyPageTitle(WhatTypeOfCompany.pageTitle)
      And("user selects 3 types of company")
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Unincorporated_builder, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Partnership, true)
      WhatTypeOfCompany.checkbox(WhatTypeOfCompany.Insurance_Assurance_company, true)
      And("user clicks the Continue button")
      WhatTypeOfCompany.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      PurchaserActingAsATrusteePage.verifyPageTitle(PurchaserActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserActingAsATrusteePage.radioButton(PurchaserActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      PurchaserActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      // uncomment next two lines when navigation to the next page is ready
//      And("clicks the Save and continue button")
//      PurchaserAndVendorConnectedPage.saveAndContinue()

      // User should be navigated to purchasers check your answers page
      // User clicks on change link for purchaser Acting as a Trustee
      // User should be navigated to purchaser Acting as a Trustee page
      // User updates purchaser Acting as a Trustee details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on change link for Are purchaser and vendor connected
      // User should be navigated to Are purchaser and vendor connected page
      // User updates Are purchaser and vendor connected details
      // User clicks on save and continue
      // User should be navigated to purchasers check your answers page
      // User clicks on save and continue

      Then("the user is navigated to the Purchaser Overview page")
      // Remove the line below once navigation from the previous page is complete
      PurchaserOverviewPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview"
      )
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Return Tasklist page")
    }

    Scenario(
      "Complete the Additional Purchaser Questions user journey for additional purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserOverviewPage.radioButton(PurchaserOverviewPage.yes)
      And("clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user should be navigated to the Purchaser Before you start page page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to the confirm page the company name of the purchaser")
      PurchaserConfirmNameOfPurchaserPage.verifyPageTitle(PurchaserConfirmNameOfPurchaserPage.pageTitle)
      And("the user clicks yes")
      PurchaserConfirmNameOfPurchaserPage.radioButton(PurchaserConfirmNameOfPurchaserPage.yes)
      And("clicks Save and Continue")
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
      // User is navigated to CYA page for additional purchaser
    }
  }
}
