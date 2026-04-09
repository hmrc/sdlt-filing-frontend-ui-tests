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
      "Complete the UK Residency Questions journey for individual non‑UK resident purchasers",
      UKResidencyJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )

      When("the user opens the uk residency questions")
      ReturnTaskList.clickLinkById("task-list-link-uk-residency-questions")

      Then("the UK Residency Before you start page is displayed")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)

      When("the user starts the uk residency questions journey")
      UKResidencyBeforeYouStart.saveAndContinue()

      Then("the Residency Status page is displayed")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)
      ResidencyStatus.clickResidencyStatusLink()

      When("the user confirms there are non-UK resident purchasers")
      ResidencyStatus.radioButton(ResidencyStatus.yes)
      ResidencyStatus.saveAndContinue()

      Then("the Crown Employment Relief page is displayed")
      CrownEmploymentRelief.verifyPageTitle(CrownEmploymentRelief.pageTitle)

      When("the user confirms there are purchasers claiming Crown Employment relief")
      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.yes)
      CrownEmploymentRelief.saveAndContinue()

      Then("the UK Residency Check Your Answers page is displayed")
      UKResidencyCheckYourAnswers.verifyPageTitle(UKResidencyCheckYourAnswers.pageTitle)

      When("the user clicks on change link and select no for claiming Crown Employment relief")
      UKResidencyCheckYourAnswers.clickCrownEmploymentReliefChange()
      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.no)
      CrownEmploymentRelief.saveAndContinue()

      And("the user clicks on change link and select no for non-UK resident purchasers")
      UKResidencyCheckYourAnswers.clickResidencyStatusChange()
      ResidencyStatus.radioButton(ResidencyStatus.no)
      ResidencyStatus.saveAndContinue()

      Then("the UK Residency Check Your Answers page is displayed")
      UKResidencyCheckYourAnswers.verifyPageTitle(UKResidencyCheckYourAnswers.pageTitle)
    }

    Scenario(
      "Complete the UK Residency Questions journey for a purchaser company with non‑UK residents and non‑UK close companies",
      UKResidencyJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("company-purchaser-with-agents-full-land-residential")
      )

      When("the user starts the uk residency questions journey")
      ReturnTaskList.clickLinkById("task-list-link-uk-residency-questions")

      Then("the UK Residency Before you start page is displayed")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)

      When("the user starts the uk residency questions journey")
      UKResidencyBeforeYouStart.saveAndContinue()

      Then("the Residency Status page is displayed")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)

      When("the user confirms there are non-UK resident purchasers")
      ResidencyStatus.radioButton(ResidencyStatus.yes)
      ResidencyStatus.saveAndContinue()

      Then("the Close Companies page is displayed")
      CloseCompaniesPage.verifyPageTitle(CloseCompaniesPage.pageTitle)

      When("the user confirms that the purchaser is a UK close company controlled by non-UK residents")
      CloseCompaniesPage.radioButton(CloseCompaniesPage.yes)
      CloseCompaniesPage.saveAndContinue()

      Then("the Crown Employment Relief page is displayed")
      CrownEmploymentRelief.verifyPageTitle(CrownEmploymentRelief.pageTitle)

      When("the user confirms there are no purchasers claiming Crown Employment relief")
      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.no)
      CrownEmploymentRelief.saveAndContinue()

      Then("the UK Residency Check Your Answers page is displayed")
      UKResidencyCheckYourAnswers.verifyPageTitle(UKResidencyCheckYourAnswers.pageTitle)

      When("the user clicks on change link and select yes for claiming Crown Employment relief")
      UKResidencyCheckYourAnswers.clickCrownEmploymentReliefChange()
      CrownEmploymentRelief.radioButton(CrownEmploymentRelief.yes)
      CrownEmploymentRelief.saveAndContinue()

      And("the user clicks on change link and select no for close companies")
      UKResidencyCheckYourAnswers.clickCloseCompaniesChange()
      CloseCompaniesPage.radioButton(CloseCompaniesPage.yes)
      CloseCompaniesPage.saveAndContinue()

      Then("the UK Residency Check Your Answers page is displayed")
      UKResidencyCheckYourAnswers.verifyPageTitle(UKResidencyCheckYourAnswers.pageTitle)
    }

    Scenario(
      "Complete the UK Residency Questions journey for a purchaser company with UK residents and UK close companies",
      UKResidencyJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("company-purchaser-with-agents-full-land-residential")
      )

      When("the user starts the uk residency questions journey")
      ReturnTaskList.clickLinkById("task-list-link-uk-residency-questions")

      Then("the UK Residency Before you start page is displayed")
      UKResidencyBeforeYouStart.verifyPageTitle(UKResidencyBeforeYouStart.pageTitle)

      When("the user starts the uk residency questions journey")
      UKResidencyBeforeYouStart.saveAndContinue()

      Then("the Residency Status page is displayed")
      ResidencyStatus.verifyPageTitle(ResidencyStatus.pageTitle)

      When("the user confirms there are no non-UK resident purchasers")
      ResidencyStatus.radioButton(ResidencyStatus.no)
      ResidencyStatus.saveAndContinue()

      Then("the Close Companies page is displayed")
      CloseCompaniesPage.verifyPageTitle(CloseCompaniesPage.pageTitle)

      When("the user confirms that the purchaser is not a UK close company controlled by non-UK residents")
      CloseCompaniesPage.radioButton(CloseCompaniesPage.no)
      CloseCompaniesPage.saveAndContinue()

      Then("the UK Residency Check Your Answers page is displayed")
      UKResidencyCheckYourAnswers.verifyPageTitle(UKResidencyCheckYourAnswers.pageTitle)
    }
  }
}
