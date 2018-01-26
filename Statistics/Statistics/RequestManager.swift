//
//  RequestManager.swift
//  ExamTest
//
//  Created by Florin Ionita on 1/25/18.
//  Copyright © 2018 mobile. All rights reserved.
//

import UIKit
import Alamofire

struct Parser {
    static let teacherName = "teacher"
    static let yearOfStudy = "yearOfStudy"
    static let startDate = "starDate"
    static let endDate = "endDate"
    static let completed = "completedAssignments"
    static let notCompleted = "unfinishedAssignments"
    static let name = "name"
    
    static let nr = "nr"
    static let total_attachments = "total_attachments"
    static let nr_max = "nr_max"
}

let baseUrl = "http://192.168.100.9:8080"
let coursesURL = "\(baseUrl)/courses"
let feedbackTeachersURL = "\(baseUrl)/feedback_teachers"
let feedbackStudentsURL = "\(baseUrl)/feedback_students"
let assignmentsFileAllURL = "\(baseUrl)/assignments_file_all"
let assignmentsFileMaxURL = "\(baseUrl)/assignments_file_max"
let assignmentsFileHaveURL = "\(baseUrl)/assignements_have_files"

class RequestManager: NSObject {

    var courses: [[String: Any]]!
    var feedbackTeachers: Int!
    var feedbackStudents: Int!
    var totalAttachments: Int!
    var maxAttachments: Int!
    var assigmentsThatHaveAttachements: Int!
    
    static private let shared = RequestManager.init()
    
    static func sharedInstance() -> RequestManager {
        return self.shared
    }
    
    private override init() {
        super.init()
    }
    
    func makeCoursesRequest(completion: @escaping (Bool) -> Void) {
        Alamofire.request(coursesURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? [[String: Any]] {
                    self.courses = data
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
    func makeFeedbackTeachersRequest(completion: @escaping (Bool) -> Void) {
        Alamofire.request(feedbackTeachersURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.feedbackTeachers = data
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
    func makeFeedbackStudentsRequest(completion: @escaping (Bool) -> Void) {
        Alamofire.request(feedbackStudentsURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.feedbackStudents = data
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
    func makeAllAssigmentsNumberRequest(completion: @escaping (Bool) -> Void) {
        Alamofire.request(assignmentsFileAllURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.totalAttachments = data
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
    func makeMaxAttachementsNumberForFile(completion: @escaping (Bool) -> Void) {
        Alamofire.request(assignmentsFileMaxURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.maxAttachments = data
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
    func makeNrAssigmentsThatHaveFileRequest(completion: @escaping (Bool) -> Void) {
        Alamofire.request(assignmentsFileHaveURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.assigmentsThatHaveAttachements = data
                    completion(true)
                }
            case .failure:
                completion(false)
            }
        }
    }
    
}
