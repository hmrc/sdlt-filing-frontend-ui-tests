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

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.UKResidency.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class UKResidencyQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend About The UK Residency Questions") {
    Scenario(
      "Complete the UK Residency Questions for an Individual Purchaser having non-uk residents",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStart.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStart.saveAndContinue()
      Then("the user is navigated to Residency Status page")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)
      When("the user selects Yes radio button")
      ResidencyStatus.radioButton(ResidencyStatus.yes)
      And("clicks on Save & Continue button")
      ResidencyStatus.saveAndContinue()
      Then("the user is navigated to the Crown Employment Relief page")
      CrownEmploymentRelief.verifyPageTitle(CrownEmploymentRelief.pageTitle)
      When("the user select Yes radio button")
      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.yes)
      // ******Uncomment below 2 lines when navigation is ready**********
//      And ("clicks on Save & Continue button")
      // CrownEmploymentRelief.saveAndContinue()
//      Then ("the user is navigated to CYA page")

    }

    Scenario(
      "Complete the UK Residency Questions for an Individual Purchaser having only uk residents",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStart.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStart.saveAndContinue()
      Then("the user is navigated to Residency Status page")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)
      When("the user selects No radio button")
      ResidencyStatus.radioButton(ResidencyStatus.no)
      // ****************Uncomment below 2 lines once navigation is ready*********
      //      And ("clicks on Save & Continue button")
      // ResidencyStatus.saveAndContinue()
      //      Then ("the user is navigated to CYA page")

    }

    Scenario(
      "Complete the UK Residency Questions where Purchaser is a Company having non-uk residents and non-uk close companies",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("company-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStart.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStart.saveAndContinue()
      Then("the user is navigated to Residency Status page")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)
      When("the user selects Yes radio button")
      ResidencyStatus.radioButton(ResidencyStatus.yes)
      And("clicks on Save & Continue button")
      ResidencyStatus.saveAndContinue()
      Then("the user is navigated to Close Companies page")
      CloseCompaniesPage.verifyPageTitle(CloseCompaniesPage.pageTitle)
      When("the user selects Yes radio button")
      CloseCompaniesPage.radioButton(CloseCompaniesPage.yes)
      And("clicks on Save & Continue button")
      CloseCompaniesPage.saveAndContinue()
      Then("the user is navigated to the Crown Employment Relief page")
      CrownEmploymentRelief.verifyPageTitle(CrownEmploymentRelief.pageTitle)
      When("the user select No radio button")
      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.no)
      // ******Uncomment below 2 lines when navigation is ready**********
      //      And ("clicks on Save & Continue button")
      // CrownEmploymentRelief.saveAndContinue()
      //      Then ("the user is navigated to CYA page")

    }

    Scenario(
      "Complete the UK Residency Questions where Purchaser is a Company having uk residents and no non-uk residents in close companies",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("company-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStart.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStart.saveAndContinue()
      Then("the user is navigated to Residency Status page")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)
      When("the user selects No radio button")
      ResidencyStatus.radioButton(ResidencyStatus.no)
      And("clicks on Save & Continue button")
      ResidencyStatus.saveAndContinue()
      Then("the user is navigated to Close Companies page")
      CloseCompaniesPage.verifyPageTitle(CloseCompaniesPage.pageTitle)
      When("the user selects No radio button")
      CloseCompaniesPage.radioButton(CloseCompaniesPage.no)
      And("clicks on Save & Continue button")
      CloseCompaniesPage.saveAndContinue()
      //      Then ("the user is navigated to CYA page")

    }
  }
}
